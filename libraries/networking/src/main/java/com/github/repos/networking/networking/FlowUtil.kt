package com.github.repos.networking.networking

import com.google.gson.Gson
import com.github.repos.core.utils.flow.asFlow
import com.github.repos.networking.networking.error.Http400Exception
import com.github.repos.networking.networking.error.Http403Exception
import com.github.repos.networking.networking.error.HttpErrorException
import com.github.repos.networking.networking.error.HttpErrorResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response


@ExperimentalCoroutinesApi
fun <T> httpFlowWithResponse(transform: suspend () -> Response<T>): Flow<Response<T>?> =
    flow {
        val result = transform()
        emit(handleHttpError(result))
    }.flowOn(Dispatchers.IO)

@ExperimentalCoroutinesApi
fun <T> httpFlow(transform: suspend () -> T): Flow<T> =
    flow {
        try {
            emit(transform())
        } catch (error: HttpException) {
            handleHttpError(error)
        }
    }.flowOn(Dispatchers.IO)

private fun convertErrorBody(throwable: HttpException): HttpErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.charStream()?.let {
            Gson().getAdapter(HttpErrorResponse::class.java).fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}

private fun convertErrorBody(body: ResponseBody?): HttpErrorResponse? {
    return try {
        body?.charStream()?.let {
            Gson().getAdapter(HttpErrorResponse::class.java).fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}

private fun handleHttpError(httpError: HttpException) {
    when (val code = httpError.code()) {
        400 -> throw Http400Exception(code, convertErrorBody(httpError))
        403 -> throw Http403Exception(code, convertErrorBody(httpError))
        else -> throw httpError
    }
}

private fun <T> handleHttpError(response: Response<T>): Response<T>? =
    if (response.isSuccessful) {
        response
    } else {
        when (response.code()) {
            400 -> throw Http400Exception(response.code(), convertErrorBody(response.errorBody()))
            else -> throw HttpErrorException(
                response.code(),
                convertErrorBody(response.errorBody())
            )
        }
    }
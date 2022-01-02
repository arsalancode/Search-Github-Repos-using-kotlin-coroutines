package com.github.repos.networking.networking.error

class Http403Exception(
    code: Int? = null,
    errorBody: HttpErrorResponse? = null
) : HttpErrorException(code, errorBody)
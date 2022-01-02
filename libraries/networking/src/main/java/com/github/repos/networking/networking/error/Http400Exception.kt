package com.github.repos.networking.networking.error

class Http400Exception(
    code: Int? = null,
    errorBody: HttpErrorResponse? = null
) : HttpErrorException(code, errorBody)
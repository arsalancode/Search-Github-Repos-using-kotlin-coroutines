package com.github.repos.networking.networking.error

open class HttpErrorException(
    val conde: Int? = null,
    val errorBody: HttpErrorResponse? = null
) : Throwable() {
    override val message: String?
        get() = super.message
}
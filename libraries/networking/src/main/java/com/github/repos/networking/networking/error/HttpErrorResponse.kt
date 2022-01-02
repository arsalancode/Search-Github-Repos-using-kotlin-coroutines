package com.github.repos.networking.networking.error

import com.google.gson.annotations.SerializedName

data class HttpErrorResponse(
    @SerializedName("error") val error: ErrorResponse?,
    @SerializedName("code") val code: Int?,
    @SerializedName("message") val message: String
)
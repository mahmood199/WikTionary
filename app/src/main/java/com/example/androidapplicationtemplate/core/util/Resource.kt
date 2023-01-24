package com.example.androidapplicationtemplate.core.util

sealed class Resource<out T> {

    class Success<out T>(val value: T) : Resource<T>()

    class Failure(
        val failureStatus: FailureStatus,
        val code: Int? = null,
        val message: String? = null
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()

    object Default : Resource<Nothing>()
}


enum class FailureStatus {
    EMPTY,
    API_FAIL,
    NO_INTERNET,
    ACCESS_DENIED,
    USER_UNAPPROVED,
    OTHER
}

object FailureCode {
    const val BAD_REQUEST = 400
    const val UNAUTHORISED = 401
    const val FORBIDDEN = 403
    const val RESOURCE_NOT_FOUND = 404
    const val REQUEST_TIMEOUT = 408
}
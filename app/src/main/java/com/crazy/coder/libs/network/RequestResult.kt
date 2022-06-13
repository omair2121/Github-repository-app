package com.crazy.coder.libs.network

// can be used for network failure, success, and loading , in our case we have only one request, so i didnt used it
sealed class RequestResult<T> {
    data class Loading<T>(val data: T?) : RequestResult<T>()
    data class Success<T>(val data: T?) : RequestResult<T>()
    data class Error<T>(val msg: T) : RequestResult<T>()
}

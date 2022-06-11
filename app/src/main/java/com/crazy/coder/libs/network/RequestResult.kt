package com.crazy.coder.libs.network

sealed class RequestResult<T> {
    data class Loading<T>(val data: T?) : RequestResult<T>()
    data class Success<T>(val data: T?) : RequestResult<T>()
    data class Error<T>(val msg: T) : RequestResult<T>()
}

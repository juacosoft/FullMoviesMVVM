package com.juacodev.fullmovies.utils

sealed class BasicResourceData<T>(
    val data: T? = null,
    val error: Throwable? = null
){
    class Success<T>(data: T): BasicResourceData<T>(data)
    class LocalData<T>(data: T): BasicResourceData<T>(data)
    class Loading<T>(data: T?=null): BasicResourceData<T>(data)
    class Error<T>(error: Throwable,data:T?=null): BasicResourceData<T>(error = error, data = data)
}

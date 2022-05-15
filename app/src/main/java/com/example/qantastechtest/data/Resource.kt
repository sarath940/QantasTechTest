package com.example.qantastechtest.data

sealed class Resource<T>(
        val data: T? = null,
        val message: String? = null
) {
    data class Success<T>(val t: T) : Resource<T>(t)
    data class Loading<T>(val t: T? = null) : Resource<T>(t)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

}
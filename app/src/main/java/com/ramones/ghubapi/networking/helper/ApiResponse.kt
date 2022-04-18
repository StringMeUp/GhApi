package com.ramones.ghubapi.networking.helper

sealed class ApiResponse<out T> {
    data class Success<out T>(val value: T?) : ApiResponse<T>()
    data class Failure<out T>(val message: String) : ApiResponse<T>()
    data class Loading<out T>(val value: T? = null) : ApiResponse<T>()
    companion object {
        fun <T> loading(value: T?): ApiResponse<T> = Loading(value)
        fun <T> success(value: T): ApiResponse<T> = Success(value)
        fun <T> failure(error_msg: String): ApiResponse<T> = Failure(error_msg)
    }
}
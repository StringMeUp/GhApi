package com.ramones.ghubapi.networking.helper

import retrofit2.Response

abstract class SimpleDataSource {

    protected suspend fun <T> handleResult(call: suspend () -> Response<T>): ApiResponse<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                response.body()?.let { return ApiResponse.success(it) }
            }
            return ApiResponse.failure(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return ApiResponse.failure(e.message ?: e.toString())
        }
    }
}
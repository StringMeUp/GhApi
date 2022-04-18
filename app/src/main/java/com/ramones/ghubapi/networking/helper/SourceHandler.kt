package com.ramones.ghubapi.networking.helper

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T, C> persistLocalEmitSource(
    apiCall: suspend () -> ApiResponse<C>,
    persist: suspend (C) -> Unit,
    observeData: (suspend () -> ApiResponse<T>)
): Flow<ApiResponse<T>> = flow {

    emit(ApiResponse.loading(null))
    emit(observeData.invoke())

    when (val responseStatus = apiCall.invoke()) {
        is ApiResponse.Success -> if (responseStatus.value != null) {
            persist.invoke(responseStatus.value)
        }
        is ApiResponse.Failure -> {
            emit(ApiResponse.failure<T>(responseStatus.message))
        }
        is ApiResponse.Loading -> emit(ApiResponse.loading(null))
    }

    emit(observeData.invoke())
}
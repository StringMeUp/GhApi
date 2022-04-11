package com.ramones.ghubapi.repository

import com.ramones.ghubapi.networking.GhApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface MainRepositoryApi {
    suspend fun getData(): Flow<String>
}

class MainRepository @Inject constructor(
    private val apiService: GhApiService
) : MainRepositoryApi {

    override suspend fun getData() = flow {
        emit("Test")
    }
}
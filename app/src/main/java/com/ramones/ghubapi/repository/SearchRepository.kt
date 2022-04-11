package com.ramones.ghubapi.repository

import com.ramones.ghubapi.networking.SearchApiService
import com.ramones.ghubapi.networking.models.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface SearchRepositoryApi {
    suspend fun searchRepositories(): Flow<SearchResponse>
}

class SearchRepository @Inject constructor(
    private val apiService: SearchApiService
) : SearchRepositoryApi {

    override suspend fun searchRepositories() = flow {
        emit(apiService.getQueriedRepos("StringMeUp", 1, 10, "stars"))
    }
}
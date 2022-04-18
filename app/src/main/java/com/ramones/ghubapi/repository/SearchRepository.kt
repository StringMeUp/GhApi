package com.ramones.ghubapi.repository

import com.ramones.ghubapi.db.dao.RepositoryDao
import com.ramones.ghubapi.db.dbmodels.Repository
import com.ramones.ghubapi.networking.helper.ApiResponse
import com.ramones.ghubapi.networking.helper.SortType
import com.ramones.ghubapi.networking.helper.persistLocalEmitSource
import com.ramones.ghubapi.util.RepositoryDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SearchRepositoryApi {
    fun searchRepositories(
        query: String,
        page: Int = 0,
        itemsPerPage: Int = 10,
        sort: String = SortType.STARS.name
    ): Flow<ApiResponse<List<Repository>>>

    suspend fun deleteRepositories(){}
}

class SearchRepository @Inject constructor(
    private val searchSource: SearchSource,
    private val repositoryDao: RepositoryDao
) : SearchRepositoryApi {

    override fun searchRepositories(
        query: String,
        page: Int,
        itemsPerPage: Int,
        sort: String
    ): Flow<ApiResponse<List<Repository>>> =
        persistLocalEmitSource(
            apiCall = { searchSource.searchQuery(query, page, itemsPerPage, sort) },
            persist = { repositoryDao.insertRepositories(RepositoryDto.fromResponse(it.repositoryResponse)) },
            observeData = { ApiResponse.success(repositoryDao.getRepositories()) })

    override suspend fun deleteRepositories() {
        repositoryDao.clearRepositories()
    }
}
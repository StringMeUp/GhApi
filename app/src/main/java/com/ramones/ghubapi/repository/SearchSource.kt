package com.ramones.ghubapi.repository

import com.ramones.ghubapi.networking.SearchApiService
import com.ramones.ghubapi.networking.helper.SimpleDataSource
import javax.inject.Inject

class SearchSource @Inject constructor(private val apiService: SearchApiService): SimpleDataSource(){
    suspend fun searchQuery(query: String, page: Int, itemsPerPage: Int, sort: String) = handleResult {
        apiService.getQueriedRepos(query = query, page = page, itemsPerPage = itemsPerPage, sort = sort)
    }
}
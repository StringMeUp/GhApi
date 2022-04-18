package com.ramones.ghubapi.networking

import com.ramones.ghubapi.networking.models.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {

    @GET("search/repositories")
    suspend fun getQueriedRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int,
        @Query("sort") sort: String
    ): Response<SearchResponse>
}
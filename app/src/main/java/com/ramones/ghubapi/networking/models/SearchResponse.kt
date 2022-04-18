package com.ramones.ghubapi.networking.models

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("items")
    val repositoryResponse: List<RepositoryResponse>
)
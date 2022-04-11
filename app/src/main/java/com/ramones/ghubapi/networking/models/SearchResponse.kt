package com.ramones.ghubapi.networking.models

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("total_count")
    val totalCount: Int = 0,
    @SerializedName("items")
    val repositories: List<Repository> = emptyList(),
    val nextPage: Int
)
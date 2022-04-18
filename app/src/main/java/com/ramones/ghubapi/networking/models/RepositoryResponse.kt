package com.ramones.ghubapi.networking.models

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    val id: Long,
    val name: String?,
    @SerializedName("full_name")
    val fullName: String?,
    val private: Boolean?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    val description: String?,
    @SerializedName("fork")
    val isFork: Boolean,
    @SerializedName("owner")
    val ownerResponse: OwnerResponse?,
    val watchers: Long?,
    val forks: Long?,
    @SerializedName("open_issues")
    val issues: Long?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    val language: String?
)

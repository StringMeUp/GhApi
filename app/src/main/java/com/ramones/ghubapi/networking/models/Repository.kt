package com.ramones.ghubapi.networking.models

import com.google.gson.annotations.SerializedName

data class Repository(
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
    val owner: Owner?,
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

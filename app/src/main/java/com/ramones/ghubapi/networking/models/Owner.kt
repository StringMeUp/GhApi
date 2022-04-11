package com.ramones.ghubapi.networking.models

import com.google.gson.annotations.SerializedName

data class Owner(
    val id: Long,
    @SerializedName("login")
    val name: String?,
    @SerializedName("avatar_url")
    val avatar: String?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    val type: String?,
    @SerializedName("site_admin")
    val isSiteAdmin: Boolean?,
    val company: String?,
    val location: String?,
    val email: String?,
    val bio: String?,
    @SerializedName("created_at")
    val createdAt: String?
)
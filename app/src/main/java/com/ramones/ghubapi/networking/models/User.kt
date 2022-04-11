package com.ramones.ghubapi.networking.models

data class User(
    val login: String?,
    val id: Long,
    val avatar_url: String?,
    val url: String?,
    val html_url: String?,
    val followers_url: String?,
    val repos_url: String?,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val bio: String?,
    val public_repos: Int?,
    val public_gists: Int?,
    val followers: Long?,
    val following: Long?,
    val created_at: String?,
    val updated_at: String?
)

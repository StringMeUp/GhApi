package com.ramones.ghubapi.db.dbmodels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Repository(
    @PrimaryKey
    val id: Long,
    val name: String?,
    val fullName: String?,
    val isPrivate: Boolean?,
    val htmlUrl: String?,
    val description: String?,
    val isFork: Boolean,
    val ownerResponse: Owner?,
    val watchers: Long?,
    val forks: Long?,
    val issues: Long?,
    val createdAt: String?,
    val updatedAt: String?,
    val language: String?
){
    data class Owner(
        @PrimaryKey
        val id: Long,
        val name: String?,
        val avatar: String?,
        val htmlUrl: String?,
        val type: String?,
        val isSiteAdmin: Boolean?,
        val company: String?,
        val location: String?,
        val email: String?,
        val bio: String?,
        val createdAt: String?
    )
}

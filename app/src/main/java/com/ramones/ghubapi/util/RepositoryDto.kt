package com.ramones.ghubapi.util

import com.ramones.ghubapi.db.dbmodels.Repository
import com.ramones.ghubapi.networking.models.RepositoryResponse

object RepositoryDto {
    fun fromResponse(responseItems: List<RepositoryResponse>) =
        responseItems.mapTo(destination = mutableListOf()) {
            Repository(
                it.id,
                it.name,
                it.fullName,
                it.private,
                it.htmlUrl,
                it.description,
                it.isFork,
                it.ownerResponse?.toOwner(),
                it.watchers,
                it.forks,
                it.issues,
                it.createdAt,
                it.updatedAt,
                it.language
            )
        }
}
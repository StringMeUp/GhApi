package com.ramones.ghubapi.util

import com.ramones.ghubapi.db.dbmodels.Repository
import com.ramones.ghubapi.networking.models.OwnerResponse

fun OwnerResponse.toOwner(): Repository.Owner {
    return Repository.Owner(
        id = this.id,
        name = this.name,
        avatar = this.avatar,
        htmlUrl = this.htmlUrl,
        type = this.type,
        isSiteAdmin = this.isSiteAdmin,
        company = this.company,
        location = this.location,
        email = this.email,
        bio = this.bio,
        createdAt = this.createdAt
    )
}
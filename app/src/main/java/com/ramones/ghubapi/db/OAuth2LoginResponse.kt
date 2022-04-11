package com.ramones.ghubapi.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OAuth2LoginResponse(
    @PrimaryKey
    val access_token: String,
    val refresh_token: String)

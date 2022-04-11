package com.ramones.ghubapi.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class SessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertOauthToken(oAuth2LoginResponse: OAuth2LoginResponse)

    @Query("SELECT * FROM  OAuth2LoginResponse")
    abstract suspend fun getToken(): OAuth2LoginResponse

    @Query("DELETE FROM OAuth2LoginResponse")
    abstract suspend fun clearToken()
}
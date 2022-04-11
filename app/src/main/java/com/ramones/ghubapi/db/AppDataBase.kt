package com.ramones.ghubapi.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [OAuth2LoginResponse::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun authDao(): SessionDao
}
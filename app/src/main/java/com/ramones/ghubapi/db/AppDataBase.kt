package com.ramones.ghubapi.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ramones.ghubapi.db.dbmodels.Repository
import com.ramones.ghubapi.db.dao.RepositoryDao
import com.ramones.ghubapi.db.dao.SessionDao

@Database(entities = [OAuth2LoginResponse::class, Repository::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun authDao(): SessionDao
    abstract fun repositoryDao(): RepositoryDao
}
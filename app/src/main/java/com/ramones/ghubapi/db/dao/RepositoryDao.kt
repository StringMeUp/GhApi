package com.ramones.ghubapi.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ramones.ghubapi.db.dbmodels.Repository

@Dao
abstract class  RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertRepositories(repositories: List<Repository>)

    @Query("SELECT * FROM Repository ")
    abstract suspend fun getRepositories(): List<Repository>

    @Query("DELETE FROM Repository")
    abstract suspend fun clearRepositories()
}
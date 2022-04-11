package com.ramones.ghubapi.di

import android.content.Context
import androidx.room.Room
import com.ramones.ghubapi.R
import com.ramones.ghubapi.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.ramones.ghubapi.db.SessionDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideOAuthDao(appDataBase: AppDataBase): SessionDao {
        return appDataBase.authDao()
    }

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            appContext.getString(R.string.gh_api_db)
        ).fallbackToDestructiveMigration().build()
    }
}
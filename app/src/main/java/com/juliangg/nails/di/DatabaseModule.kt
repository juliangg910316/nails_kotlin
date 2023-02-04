package com.juliangg.nails.di

import android.content.Context
import com.juliangg.nails.database.AppDatabase
import com.juliangg.nails.database.turn.TurnDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideTurnDao(appDatabase: AppDatabase): TurnDao{
        return appDatabase.turnDao()
    }
}
package com.xs.rolladice.di

import android.app.Application
import androidx.room.Room
import com.xs.rolladice.data.HistoryRollDao
import com.xs.rolladice.data.HistoryRollDatabase
import com.xs.rolladice.data.NameDao
import com.xs.rolladice.data.NameDatabase
import com.xs.rolladice.repository.HistoryRollRepository
import com.xs.rolladice.repository.HistoryRollRepositoryImpl
import com.xs.rolladice.repository.NameRepository
import com.xs.rolladice.repository.NameRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesNameDatabase(app: Application): NameDatabase {
        return Room.databaseBuilder(
            app,
            NameDatabase::class.java,
            "name_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNameDao(database: NameDatabase) = database.nameDao

    @Provides
    @Singleton
    fun providesNameRepository(dao: NameDao): NameRepository {
        return NameRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun providesHistoryRollDatabase(app: Application): HistoryRollDatabase {
        return Room.databaseBuilder(
            app,
            HistoryRollDatabase::class.java,
            "historyRoll_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideHistoryRollDao(database: HistoryRollDatabase) = database.historyRollDao


    @Provides
    @Singleton
    fun providesHistoryRollRepository(dao: HistoryRollDao): HistoryRollRepository {
        return HistoryRollRepositoryImpl(dao)

    }
}
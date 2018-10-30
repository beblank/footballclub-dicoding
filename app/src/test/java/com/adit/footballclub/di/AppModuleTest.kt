package com.adit.footballclub.di

import android.app.Application
import android.arch.persistence.room.Room
import androidx.test.InstrumentationRegistry
import com.adit.footballclub.entity.repository.local.Database
import com.adit.footballclub.entity.repository.local.EventDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModuleTest(val app: Application){
    @Provides
    @Singleton
    fun provideApp(): Application = app

    @Provides
    @Singleton
    fun provideEventsDatabase(app: Application): Database =
            Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), Database::class.java).allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun provideEventDao(database: Database): EventDao = database.eventDao()
}
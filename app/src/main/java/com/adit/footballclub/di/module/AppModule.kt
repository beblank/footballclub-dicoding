package com.adit.footballclub.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.adit.footballclub.entity.repository.local.Database
import com.adit.footballclub.entity.repository.local.EventDao
import com.adit.footballclub.entity.repository.local.TeamDao
import com.adit.footballclub.utils.Const
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app:Application){
    @Provides
    @Singleton
    fun provideApp():Application = app

    @Provides
    @Singleton
    fun provideEventsDatabase(app: Application): Database = Room.databaseBuilder(app,
            Database::class.java, Const.DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideEventDao(database: Database): EventDao = database.eventDao()

    @Provides
    @Singleton
    fun provideTeamDao(database: Database): TeamDao = database.teamDao()
}
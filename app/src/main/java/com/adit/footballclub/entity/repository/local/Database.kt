package com.adit.footballclub.entity.repository.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.adit.footballclub.entity.Events
import com.adit.footballclub.entity.Team

@Database(entities = [Events::class, Team::class], version = 4, exportSchema = false)
abstract class Database:RoomDatabase(){
    abstract fun eventDao():EventDao
    abstract fun teamDao():TeamDao
}
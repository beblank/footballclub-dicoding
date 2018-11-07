package com.adit.footballclub.entity.repository.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.adit.footballclub.entity.Events

@Database(entities = [(Events::class)], version = 3, exportSchema = false)
abstract class Database:RoomDatabase(){
    abstract fun eventDao():EventDao
}
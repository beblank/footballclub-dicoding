package com.adit.footballclub.entity.repository.local

import android.arch.persistence.room.*
import com.adit.footballclub.entity.Events
import io.reactivex.Single

@Dao
interface EventDao{
    @Query("SELECT * FROM event")
    fun getEvent(): Single<List<Events>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvent(event:Events)

    @Delete
    fun deleteEvent(event:Events)

}
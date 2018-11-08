package com.adit.footballclub.entity.repository.local

import android.arch.persistence.room.*
import com.adit.footballclub.entity.Team
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface TeamDao{
    @Query("SELECT * FROM event")
    fun getTeam(): Single<List<Team>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeam(event:Team)

    @Delete
    fun deleteTeam(event:Team)

    @Query("SELECT * FROM event WHERE id = :id")
    fun searchAddedID(id:String): Maybe<Team>

}
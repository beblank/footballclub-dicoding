package com.adit.footballclub.entity.repository.local

import android.arch.persistence.room.*
import com.adit.footballclub.entity.Team
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface TeamDao{
    @Query("SELECT * FROM team")
    fun getTeam(): Single<List<Team>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeam(team:Team)

    @Delete
    fun deleteTeam(team:Team)

    @Query("SELECT * FROM team WHERE id = :id")
    fun searchAddedID(id:String): Maybe<Team>

}
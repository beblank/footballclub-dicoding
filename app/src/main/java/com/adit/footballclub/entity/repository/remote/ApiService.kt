package com.adit.footballclub.entity.repository.remote

import com.adit.footballclub.entity.ListEvent
import com.adit.footballclub.entity.ListTeam
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET("eventspastleague.php")
    fun getLastMatch(@Query("id") id:String) : Observable<ListEvent>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id:String) : Observable<ListEvent>

    @GET("lookupteam.php")
    fun getTeamDetail(@Query("id") id:String) : Observable<ListTeam>

    @GET("search_all_teams.php")
    fun getAllTeam(@Query("l") name:String) : Observable<ListTeam>
}
package com.adit.footballclub.entity.repository.remote

import com.adit.footballclub.entity.Events
import com.adit.footballclub.entity.repository.ListEvent
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET("eventspastleague.php")
    fun getLastmatch(@Query("id") id:String) : Observable<ListEvent>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id:String) : Observable<ListEvent>
}
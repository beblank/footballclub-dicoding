package com.adit.footballclub.entity.repository.remote

import com.adit.footballclub.entity.ListEvent
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET("eventspastleague.php")
    fun getLastMatch(@Query("id") id:String) : Observable<ListEvent>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id:String) : Observable<ListEvent>


}
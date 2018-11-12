package com.adit.footballclub.entity.repository

import com.adit.footballclub.entity.ListPlayer
import com.adit.footballclub.entity.repository.remote.ApiService
import io.reactivex.Observable
import javax.inject.Inject

class PlayerRepository @Inject constructor(val apiService: ApiService){
    fun getPlayerDetail(name:String): Observable<ListPlayer> = apiService.getPlayerDetail(name)
    fun getAllPlayer(team:String):Observable<ListPlayer> = apiService.getAllPleyer(team)
}
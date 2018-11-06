package com.adit.footballclub.entity.repository

import com.adit.footballclub.entity.ListTeam
import com.adit.footballclub.entity.repository.remote.ApiService
import io.reactivex.Observable
import javax.inject.Inject

class TeamRepository @Inject constructor( val apiService: ApiService){
    fun getTeamDetail(id:String):Observable<ListTeam> = apiService.getTeamDetail(id)
    fun getAllTeam(name:String):Observable<ListTeam> = apiService.getAllTeam(name)
}
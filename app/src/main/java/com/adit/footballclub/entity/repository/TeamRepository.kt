package com.adit.footballclub.entity.repository

import com.adit.footballclub.entity.ListTeam
import com.adit.footballclub.entity.Team
import com.adit.footballclub.entity.repository.local.TeamDao
import com.adit.footballclub.entity.repository.remote.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TeamRepository @Inject constructor( val apiService: ApiService, val teamDao: TeamDao){
    fun getTeamDetail(id:String):Observable<ListTeam> = apiService.getTeamDetail(id)
    fun getAllTeam(name:String):Observable<ListTeam> = apiService.getAllTeam(name)

    fun getTeamsFromDB() = teamDao.getTeam()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun insertTeamtoDB(team: Team) = Observable.fromCallable { teamDao.insertTeam(team) }
    fun deleteTeamfromDB(team: Team) = Observable.fromCallable { teamDao.deleteTeam(team) }

    fun checkFavorite(id:String) = teamDao.searchAddedID(id)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
package com.adit.footballclub.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.adit.footballclub.entity.Events
import com.adit.footballclub.entity.Team
import com.adit.footballclub.entity.repository.TeamRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TeamViewModel @Inject constructor(
        private val teamRepository: TeamRepository): ViewModel(){

    private val compositeDisposable = CompositeDisposable()
    private val listHomeTeam: MutableLiveData<Team> = MutableLiveData()
    private val listAwayTeam: MutableLiveData<Team> = MutableLiveData()
    private val listTeamError: MutableLiveData<String> = MutableLiveData()
    private val allTeams:MutableLiveData<List<Team>> = MutableLiveData()
    private val allTeamsError:MutableLiveData<String> = MutableLiveData()


    fun getListTeamError(): MutableLiveData<String> = listTeamError
    fun getListHomeTeam(): MutableLiveData<Team> = listHomeTeam
    fun getListAwayTeam(): MutableLiveData<Team> = listAwayTeam
    fun getAllTeam(): MutableLiveData<List<Team>> = allTeams
    fun getAllTeamError(): MutableLiveData<String> = allTeamsError

    fun getAwayTeams(id:String){
        compositeDisposable.add(teamRepository.getTeamDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    listAwayTeam.value = it.teams[0]
                })
    }

    fun getAllTeamByName(name:String){
        compositeDisposable.add(teamRepository.getAllTeam(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    allTeams.value = it.teams
                },{
                    allTeamsError.value = it.message
                }))
    }



    fun getHomeTeams(id:String){
        compositeDisposable.add(teamRepository.getTeamDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    listHomeTeam.value = it.teams[0]
                })
    }

    fun disposeElements(){
        compositeDisposable.dispose()
    }



}
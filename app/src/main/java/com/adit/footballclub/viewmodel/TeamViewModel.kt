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
import javax.inject.Singleton

class TeamViewModel @Inject constructor(
        private val teamRepository: TeamRepository): ViewModel(){

    private val compositeDisposable = CompositeDisposable()
    private val listHomeTeam: MutableLiveData<Team> = MutableLiveData()
    private val listAwayTeam: MutableLiveData<Team> = MutableLiveData()
    private val listTeamError: MutableLiveData<String> = MutableLiveData()
    private val allTeams:MutableLiveData<List<Team>> = MutableLiveData()
    private val allTeamsError:MutableLiveData<String> = MutableLiveData()
    private val selectedTeam:MutableLiveData<Team> = MutableLiveData()
    private val favTeam:MutableLiveData<Team> = MutableLiveData()
    private val insertStatus:MutableLiveData<Boolean> = MutableLiveData()
    private val deleteStatus:MutableLiveData<Boolean> = MutableLiveData()


    fun getListTeamError(): MutableLiveData<String> = listTeamError
    fun getListHomeTeam(): MutableLiveData<Team> = listHomeTeam
    fun getListAwayTeam(): MutableLiveData<Team> = listAwayTeam
    fun getAllTeam(): MutableLiveData<List<Team>> = allTeams
    fun getAllTeamError(): MutableLiveData<String> = allTeamsError
    fun getSelectedTeam():MutableLiveData<Team> = selectedTeam
    fun getFavTeam():MutableLiveData<Team> = favTeam


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

    @Singleton
    fun getTeamfromDB(){
        compositeDisposable.add(
                teamRepository.getTeamsFromDB()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError { allTeamsError.value = it.message }
                        .subscribe { allTeams.value = it }
        )
    }

    fun insertTeam(team: Team){
        compositeDisposable.add(
                teamRepository.insertTeamtoDB(team)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext { insertStatus.value = true }
                        .doOnError { insertStatus.value = false }
                        .subscribe())
    }

    fun deleteTeam(team: Team){
        compositeDisposable.add(
                teamRepository.deleteTeamfromDB(team)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext { deleteStatus.value = true }
                        .doOnError { deleteStatus.value = false }
                        .subscribe()
        )
    }

    fun checkTeam(id: String){
        compositeDisposable.add(teamRepository.checkFavorite(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    favTeam.value = it
                })
    }



}
package com.adit.footballclub.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.adit.footballclub.entity.ListTeam
import com.adit.footballclub.entity.Team
import com.adit.footballclub.entity.repository.TeamRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailActivityViewModel @Inject constructor(
        val teamRepository: TeamRepository):ViewModel(){

    private val listHomeTeam:MutableLiveData<Team> = MutableLiveData()
    private val listAwayTeam:MutableLiveData<Team> = MutableLiveData()
    private val listTeamError:MutableLiveData<String> = MutableLiveData()

    val compositeDisposable = CompositeDisposable()

    fun getListTeamError():MutableLiveData<String> = listTeamError
    fun getListHomeTeam():MutableLiveData<Team> = listHomeTeam
    fun getListAwayTeam():MutableLiveData<Team> = listHomeTeam

    fun getAwayTeams(id:String){
        compositeDisposable.add(teamRepository.getTeamDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    listAwayTeam.value = it.teams[0]
                })
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
        compositeDisposable.clear()
    }
}
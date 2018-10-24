package com.adit.footballclub.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.adit.footballclub.entity.Events
import com.adit.footballclub.entity.ListTeam
import com.adit.footballclub.entity.Team
import com.adit.footballclub.entity.repository.EventsRepository
import com.adit.footballclub.entity.repository.TeamRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailActivityViewModel @Inject constructor(
        val teamRepository: TeamRepository, val eventsRepository: EventsRepository):ViewModel(){

    private val listHomeTeam:MutableLiveData<Team> = MutableLiveData()
    private val listAwayTeam:MutableLiveData<Team> = MutableLiveData()
    private val listTeamError:MutableLiveData<String> = MutableLiveData()
    private val event:MutableLiveData<Events> = MutableLiveData()

    val compositeDisposable = CompositeDisposable()

    fun getListTeamError():MutableLiveData<String> = listTeamError
    fun getListHomeTeam():MutableLiveData<Team> = listHomeTeam
    fun getListAwayTeam():MutableLiveData<Team> = listAwayTeam

    fun getAwayTeams(id:String){
        compositeDisposable.add(teamRepository.getTeamDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    listAwayTeam.value = it.teams[0]
                })
    }

    fun insertEvent(events: Events){
        compositeDisposable.add(
                eventsRepository.insertEventtoDB(events)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe())
    }

    fun getEventById() = event

    fun checkEvent(id: String){
        compositeDisposable.add(eventsRepository.checkFavorite(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    event.value = it
                    Log.d("dodol", "event is $it")
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
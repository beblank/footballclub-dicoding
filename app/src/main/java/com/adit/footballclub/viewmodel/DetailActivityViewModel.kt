package com.adit.footballclub.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.adit.footballclub.entity.ListTeam
import com.adit.footballclub.entity.Team
import com.adit.footballclub.entity.repository.TeamRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailActivityViewModel @Inject constructor(
        val teamRepository: TeamRepository):ViewModel(){

    private val listTeam:MutableLiveData<ListTeam> = MutableLiveData()
    private val listTeamError:MutableLiveData<String> = MutableLiveData()

    lateinit var disposableObserver: DisposableObserver<ListTeam>

    fun getListTeamError():MutableLiveData<String> = listTeamError
    fun getListTeam():MutableLiveData<ListTeam> = listTeam

    fun getTeams(id:String){
        disposableObserver = object : DisposableObserver<ListTeam>(){

            override fun onComplete() {
            }

            override fun onNext(t: ListTeam) {
                listTeam.value = t
            }

            override fun onError(e: Throwable) {
                listTeamError.value = e.message
            }
        }

        teamRepository.getTeamDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver)
    }
}
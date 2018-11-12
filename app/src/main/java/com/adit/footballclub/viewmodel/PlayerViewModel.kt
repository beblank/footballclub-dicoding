package com.adit.footballclub.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.adit.footballclub.entity.Player
import com.adit.footballclub.entity.repository.PlayerRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlayerViewModel @Inject constructor(
        val playerRepository: PlayerRepository):ViewModel(){

    private val compositeDisposable = CompositeDisposable()
    private val allPlayer:MutableLiveData<List<Player>> = MutableLiveData()
    private val playerError:MutableLiveData<String> = MutableLiveData()

    fun getAllPlayer() = allPlayer
    fun getPlayerError() = playerError

    fun getAllPlayerFromApi(team:String){
        compositeDisposable.add(playerRepository.getAllPlayer(team)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    allPlayer.value = it.player
                },{
                    playerError.value = it.message
                }))
    }
}
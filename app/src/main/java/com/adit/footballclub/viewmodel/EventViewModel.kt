package com.adit.footballclub.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.adit.footballclub.utils.Const
import com.adit.footballclub.entity.Events
import com.adit.footballclub.entity.repository.EventsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

class EventViewModel @Inject constructor(
    private val eventsRepository: EventsRepository):ViewModel(){
    private val selectedTab:MutableLiveData<Int> = MutableLiveData()
    private val listEvents:MutableLiveData<List<Events>> = MutableLiveData()
    private val listEventsError:MutableLiveData<String> = MutableLiveData()
    private val leagueID:MutableLiveData<String> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()
    private val insertStatus:MutableLiveData<Boolean> = MutableLiveData()
    private val deleteStatus:MutableLiveData<Boolean> = MutableLiveData()
    private val event: MutableLiveData<Events> = MutableLiveData()

    init {
        selectedTab.value = Const.nextMatchTab
        leagueID.value = Const.id
    }

    fun getSelectedTab() = selectedTab
    fun getListEvents():MutableLiveData<List<Events>> = listEvents
    fun getListEventsError():MutableLiveData<String> = listEventsError
    fun getLeagueID():MutableLiveData<String> = leagueID
    fun getInsertStatus() = insertStatus
    fun getDeleteStatus() = deleteStatus
    fun getEventById() = event

    @Singleton
    fun getEventsfromApi(id:String, tabs:Int):Boolean{
        compositeDisposable.add(
                eventsRepository.getEvents(id, tabs)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { listEventsError.value = it.message }
                .subscribe { listEvents.value = it.events }
        )
        return true
    }

    @Singleton
    fun getEventsfromDB(){
        compositeDisposable.add(
                eventsRepository.getEventsFromDB()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError { listEventsError.value = it.message }
                        .subscribe { listEvents.value = it }
        )
    }

    fun insertEvent(events: Events){
        compositeDisposable.add(
                eventsRepository.insertEventtoDB(events)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext { insertStatus.value = true }
                        .doOnError { insertStatus.value = false }
                        .subscribe())
    }

    fun deleteEvent(events: Events){
        compositeDisposable.add(
                eventsRepository.deleteEventfromDB(events)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext { deleteStatus.value = true }
                        .doOnError { deleteStatus.value = false }
                        .subscribe()
        )
    }

    fun checkEvent(id: String){
        compositeDisposable.add(eventsRepository.checkFavorite(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    event.value = it
                })
    }

    fun disposeElements(){
        compositeDisposable.dispose()
    }
}
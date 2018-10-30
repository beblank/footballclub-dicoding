package com.adit.footballclub.entity.repository

import android.util.Log
import com.adit.footballclub.entity.Events
import com.adit.footballclub.utils.Const
import com.adit.footballclub.entity.ListEvent
import com.adit.footballclub.entity.repository.local.EventDao
import com.adit.footballclub.entity.repository.remote.ApiService
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EventsRepository @Inject constructor(val apiService: ApiService, val eventsDao: EventDao){

    fun getEvents(id:String, tabs:Int): Observable<ListEvent>{
        var result:Observable<ListEvent>? = null
        when (tabs){
            Const.lastMatchTab -> result = apiService.getLastMatch(id)
            Const.nextMatchTab -> result = apiService.getNextMatch(id)
        }
        return result!!
    }

    fun getEventsFromDB() = eventsDao.getEvent()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { Log.d("dodol", "db : ${it}") }

    fun insertEventtoDB(events: Events) = Observable.fromCallable { eventsDao.insertEvent(events) }
    fun deleteEventfromDB(events: Events) = Observable.fromCallable { eventsDao.deleteEvent(events) }

    fun checkFavorite(id:String) = eventsDao.searchAddedID(id)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { Log.d("dodol", "db : ${it}") }
}
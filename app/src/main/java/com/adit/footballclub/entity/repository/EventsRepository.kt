package com.adit.footballclub.entity.repository

import com.adit.footballclub.utils.Const
import com.adit.footballclub.entity.ListEvent
import com.adit.footballclub.entity.repository.local.EventDao
import com.adit.footballclub.entity.repository.remote.ApiService
import io.reactivex.Observable
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
}
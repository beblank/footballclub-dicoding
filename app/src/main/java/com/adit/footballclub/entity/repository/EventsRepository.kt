package com.adit.footballclub.entity.repository

import com.adit.footballclub.entity.repository.remote.ApiService
import io.reactivex.Observable
import javax.inject.Inject

class EventsRepository @Inject constructor(val apiService: ApiService){
    fun getLastEvents(id:String): Observable<ListEvent> = apiService.getLastmatch(id)
}
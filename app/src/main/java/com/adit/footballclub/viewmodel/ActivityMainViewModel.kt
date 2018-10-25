package com.adit.footballclub.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.adit.footballclub.utils.Const
import com.adit.footballclub.entity.Events
import com.adit.footballclub.entity.ListEvent
import com.adit.footballclub.entity.repository.EventsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

class ActivityMainViewModel @Inject constructor(
    val eventsRepository: EventsRepository):ViewModel(){
    private val listEvents:MutableLiveData<List<Events>> = MutableLiveData()
    private val listEventsError:MutableLiveData<String> = MutableLiveData()
    val compositeDisposable = CompositeDisposable()
    fun getListEvents():MutableLiveData<List<Events>> = listEvents
    fun getListEventsError():MutableLiveData<String> = listEventsError

    @Singleton
    fun getEventsfromApi(tabs:Int){
        compositeDisposable.add(
                eventsRepository.getEvents(Const.id, tabs)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { listEventsError.value = it.message }
                .subscribe { listEvents.value = it.events }
        )

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

    fun disposeElements(){
        compositeDisposable.dispose()
    }
}
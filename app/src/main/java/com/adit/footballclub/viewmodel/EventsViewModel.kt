package com.adit.footballclub.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.adit.footballclub.Utils.Const
import com.adit.footballclub.entity.Events
import com.adit.footballclub.entity.repository.EventsRepository
import com.adit.footballclub.entity.repository.ListEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EventsViewModel @Inject constructor(
        private val eventsRepository: EventsRepository):ViewModel(){


    private val listEvents:MutableLiveData<List<Events>> = MutableLiveData()
    private val listEventsError:MutableLiveData<String> = MutableLiveData()

    lateinit var disposableObserver: DisposableObserver<ListEvent>

    fun getListEvents():MutableLiveData<List<Events>> = listEvents
    fun getListEventsError():MutableLiveData<String> = listEventsError

    fun getEvents(){
        disposableObserver = object :DisposableObserver<ListEvent>(){

            override fun onComplete() {
            }

            override fun onNext(t: ListEvent) {
                listEvents.value = t.events
            }

            override fun onError(e: Throwable) {
                listEventsError.value = e.message
            }
        }
        eventsRepository.getLastEvents(Const.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver)

    }

    fun disposeElements(){
        if (null != disposableObserver && disposableObserver.isDisposed) disposableObserver.dispose()
    }


}
package com.adit.footballclub.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.adit.footballclub.utils.Const
import com.adit.footballclub.entity.Events
import com.adit.footballclub.entity.ListEvent
import com.adit.footballclub.entity.repository.EventsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

class ActivityMainViewModel @Inject constructor(
    val eventsRepository: EventsRepository):ViewModel(){
    private val selectedTab:MutableLiveData<Int> = MutableLiveData()
    private val listEvents:MutableLiveData<List<Events>> = MutableLiveData()
    private val listEventsError:MutableLiveData<String> = MutableLiveData()
    lateinit var disposableObserver: DisposableObserver<ListEvent>

    init {
        selectedTab.value = Const.lastMatchTab
    }

    fun getSelectedTab() = selectedTab
    fun getListEvents():MutableLiveData<List<Events>> = listEvents
    fun getListEventsError():MutableLiveData<String> = listEventsError

    @Singleton
    fun getEvents(tabs:Int){
        disposableObserver = object : DisposableObserver<ListEvent>(){

            override fun onComplete() {
            }

            override fun onNext(t: ListEvent) {
                listEvents.value = t.events
            }

            override fun onError(e: Throwable) {
                listEventsError.value = e.message
            }
        }
        eventsRepository.getEvents(Const.id, tabs)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver)

    }

    fun disposeElements(){
        if (null != disposableObserver && disposableObserver.isDisposed) disposableObserver.dispose()
    }
}
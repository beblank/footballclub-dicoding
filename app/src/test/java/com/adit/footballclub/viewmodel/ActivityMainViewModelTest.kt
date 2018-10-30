package com.adit.footballclub.viewmodel

import com.adit.footballclub.entity.ListEvent
import com.adit.footballclub.entity.repository.EventsRepository
import com.adit.footballclub.entity.repository.local.EventDao
import com.adit.footballclub.entity.repository.remote.ApiService
import com.adit.footballclub.utils.ApiUtils
import com.adit.footballclub.utils.Const
import com.adit.footballclub.utils.POST_MOCK_PATH
import com.adit.footballclub.utils.RxImmediateSchedulerRule
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import java.util.concurrent.TimeUnit

class ActivityMainViewModelTest{



    @Before
    fun setup(){
    }


    @Test
    fun checkPastEventMatchSize(){


    }

    fun testDataObservable():Observable<ListEvent>{
        return Observable.just(ApiUtils.getUrl(POST_MOCK_PATH))
    }
}
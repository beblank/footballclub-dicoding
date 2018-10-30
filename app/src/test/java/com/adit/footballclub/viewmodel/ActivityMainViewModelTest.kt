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

    @Rule @JvmField var rule = MockitoJUnit.rule()
    @Rule @JvmField var Rxrule = RxImmediateSchedulerRule()

    lateinit var activityMainViewModel:ActivityMainViewModel
    @Mock
    lateinit var apiService: ApiService
    @Mock
    lateinit var eventDao: EventDao
    lateinit var eventsRepository: EventsRepository
    lateinit var testObserver:TestObserver<ListEvent>

    @Before
    fun setup(){

        MockitoAnnotations.initMocks(this)
        eventsRepository = EventsRepository(apiService, eventDao)
        activityMainViewModel = ActivityMainViewModel(eventsRepository)
        `when`(eventsRepository.getEvents(Const.id, Const.lastMatchTab)).thenReturn(testDataObservable())
    }


    @Test
    fun checkPastEventMatchSize(){
        activityMainViewModel.getEventsfromApi(Const.lastMatchTab)
        //testObserver = eventsRepository.getEvents(Const.id, 0).debounce(20, TimeUnit.SECONDS).test()
        testObserver.awaitTerminalEvent()
        testObserver.assertNoErrors()
                .assertComplete()
                .assertValue{l -> l.events.size == 15}
    }

    fun testDataObservable():Observable<ListEvent>{
        return Observable.just(ApiUtils.getUrl(POST_MOCK_PATH))
    }
}
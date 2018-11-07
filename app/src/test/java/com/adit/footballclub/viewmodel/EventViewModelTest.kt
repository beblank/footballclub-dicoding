package com.adit.footballclub.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.adit.footballclub.BaseTest
import com.adit.footballclub.entity.Events
import com.adit.footballclub.entity.repository.EventsRepository
import com.adit.footballclub.utils.Const
import com.adit.footballclub.utils.RxImmediateSchedulerRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@RunWith(RobolectricTestRunner::class)
class EventViewModelTest:BaseTest(){

    @Rule
    @JvmField
    val rxTestRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var activity:FragmentActivity
    lateinit var viewModel:EventViewModel
    //lateinit var detailViewModel:DetailActivityViewModel

    @Before
    override fun setup(){
        super.setup()
        activity = Robolectric.setupActivity(FragmentActivity::class.java)
        viewModel = ViewModelProviders.of(activity, viewModelFactory)[EventViewModel::class.java]
        //detailViewModel = ViewModelProviders.of(activity, viewModelFactory)[DetailActivityViewModel::class.java]

    }


    @Test
    fun checkPastEventMatch(){
        assertEquals(null, viewModel.getListEvents().value, "check if event still null")
        //viewModel.getEventsfromApi(Const.lastMatchTab)
        assertNotEquals(null, viewModel.getListEvents().value, "event should be not null")
        assertEquals(null, viewModel.getListEventsError().value, "error shoule be null")
        assertEquals(15, viewModel.getListEvents().value!!.size, "event list size should be 15")
        assertNotEquals(null, viewModel.getListEvents().value!![0].homeScore, "home score should be not null")
        assertNotEquals(null, viewModel.getListEvents().value!![0].awayScore, "away score should be not null")
    }

    @Test
    fun checkNextEventMatch(){
        assertEquals(null, viewModel.getListEvents().value, "check if event still null")
        //viewModel.getEventsfromApi(Const.nextMatchTab)
        assertNotEquals(null, viewModel.getListEvents().value, "event should be not null")
        assertEquals(null, viewModel.getListEventsError().value, "error shoule be null")
        assertEquals(15, viewModel.getListEvents().value!!.size, "event list size should be 15")
        assertEquals(null, viewModel.getListEvents().value!![0].homeScore, "home score should be null")
        assertEquals(null, viewModel.getListEvents().value!![0].awayScore, "away score should be null")
    }

    @Test
    fun getEventFromDB(){
        assertEquals(null, viewModel.getListEvents().value, "check if event still null")
//        detailViewModel.insertEvent(Events("test",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                ""))
        viewModel.getEventsfromDB()
        assertNotEquals(null, viewModel.getListEvents().value, "event should be not null")
        assertEquals(1, viewModel.getListEvents().value!!.size, "event should be not null")
        assertEquals("test", viewModel.getListEvents().value!![0].idEvent, "event should be not null")
    }

    @Test
    fun deleteEventfromDB(){
        assertEquals(null, viewModel.getListEvents().value, "check if event still null")
//        val event = Events("delete",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "")
        //detailViewModel.insertEvent(event)
        viewModel.getEventsfromDB()
        assertNotEquals(null, viewModel.getListEvents().value, "event should be not null")
        assertEquals(1, viewModel.getListEvents().value!!.size, "event should be not null")
        assertEquals("delete", viewModel.getListEvents().value!![0].idEvent, "event should be not null")
        //detailViewModel.deleteEvent(event)
        viewModel.getEventsfromDB()
        assertEquals(0, viewModel.getListEvents().value!!.size, "event should be not null")

    }
}
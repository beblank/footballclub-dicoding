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
import java.net.HttpURLConnection
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
    lateinit var teamViewModel: TeamViewModel
    val ID = "4328"

    override fun isMockServerEnabled() = true

    @Before
    override fun setup(){
        super.setup()
        activity = Robolectric.setupActivity(FragmentActivity::class.java)
        viewModel = ViewModelProviders.of(activity, viewModelFactory)[EventViewModel::class.java]
        teamViewModel = ViewModelProviders.of(activity, viewModelFactory)[TeamViewModel::class.java]

    }

    @Test
    fun checkPastEventMatch(){
        mockHttpResponse("prevmatch.json", HttpURLConnection.HTTP_OK)
        assertEquals(null, viewModel.getListEvents().value, "check if event still null")
        viewModel.getEventsfromApi(ID, Const.lastMatchTab)
        assertNotEquals(null, viewModel.getListEvents().value, "event should be not null")
        assertEquals(null, viewModel.getListEventsError().value, "error shoule be null")
        assertEquals(15, viewModel.getListEvents().value!!.size, "event list size should be 15")
        assertNotEquals(null, viewModel.getListEvents().value!![0].homeScore, "home score should be not null")
        assertNotEquals(null, viewModel.getListEvents().value!![0].awayScore, "away score should be not null")
    }

    @Test
    fun checkNextEventMatch(){
        mockHttpResponse("nextmatch.json", HttpURLConnection.HTTP_OK)
        assertEquals(null, viewModel.getListEvents().value, "check if event still null")
        viewModel.getEventsfromApi(ID, Const.nextMatchTab)
        assertNotEquals(null, viewModel.getListEvents().value, "event should be not null")
        assertEquals(null, viewModel.getListEventsError().value, "error shoule be null")
        assertEquals(15, viewModel.getListEvents().value!!.size, "event list size should be 15")
        assertEquals(null, viewModel.getListEvents().value!![0].homeScore, "home score should be null")
        assertEquals(null, viewModel.getListEvents().value!![0].awayScore, "away score should be null")
    }

    @Test
    fun getEventFromDB(){
        assertEquals(null, viewModel.getListEvents().value, "check if event still null")
        viewModel.insertEvent(Events("test",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""))
        viewModel.getEventsfromDB()
        assertNotEquals(null, viewModel.getListEvents().value, "event should be not null")
        assertEquals(1, viewModel.getListEvents().value!!.size, "event should be not null")
        assertEquals("test", viewModel.getListEvents().value!![0].idEvent, "event should be not null")
    }

    @Test
    fun deleteEventfromDB(){
        assertEquals(null, viewModel.getListEvents().value, "check if event still null")
        val event = Events("delete",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "")
        viewModel.insertEvent(event)
        viewModel.getEventsfromDB()
        assertNotEquals(null, viewModel.getListEvents().value, "event should be not null")
        assertEquals(1, viewModel.getListEvents().value!!.size, "event should be not null")
        assertEquals("delete", viewModel.getListEvents().value!![0].idEvent, "event should be not null")
        viewModel.deleteEvent(event)
        viewModel.getEventsfromDB()
        assertEquals(0, viewModel.getListEvents().value!!.size, "event should be not null")

    }

    @Test
    @Throws(Exception::class)
    fun checkFavorite(){
        assertEquals(null, viewModel.getEventById().value, "favorite event should be null")
        viewModel.checkEvent(ID)
        assertEquals(null, viewModel.getEventById().value, "favorite event should be null since db is droped on test")
    }

    @Test
    fun filterMatch(){
        mockHttpResponse("nextmatch.json", HttpURLConnection.HTTP_OK)
        assertEquals(null, viewModel.getListEvents().value, "check if event still null")
        viewModel.getEventsfromApi(ID, Const.nextMatchTab)
        assertNotEquals(null, viewModel.getListEvents().value, "event should be not null")
        assertEquals(null, viewModel.getListEventsError().value, "error shoule be null")
        assertEquals(15, viewModel.getListEvents().value!!.size, "event list size should be 15")
        assertEquals(null, viewModel.getListEvents().value!![0].homeScore, "home score should be null")
        assertEquals(null, viewModel.getListEvents().value!![0].awayScore, "away score should be null")
        val filter1 = "Totte"
        viewModel.filterEvent(filter1)
        assertEquals(true, viewModel.getListEvents().value!![0].eventStr!!.contains(filter1), "filter text should show filtered data")
        val filter2 = "asd"
        viewModel.filterEvent(filter2)
        assertEquals(false, viewModel.getListEvents().value!![0].eventStr!!.toLowerCase().contains(filter2), "not find filtered data ")

    }
}
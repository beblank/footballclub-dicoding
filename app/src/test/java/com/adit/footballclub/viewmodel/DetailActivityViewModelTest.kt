package com.adit.footballclub.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.adit.footballclub.BaseTest
import com.adit.footballclub.utils.RxImmediateSchedulerRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import java.lang.Exception
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@RunWith(RobolectricTestRunner::class)
class DetailActivityViewModelTest: BaseTest(){

    @Rule
    @JvmField
    val rxTestRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var activity: FragmentActivity
    lateinit var viewModel:DetailActivityViewModel
    val  homeID = "133604"
    val  awayID = "133602"
    val eventId= "576570"

    @Before
    override fun setup(){
        super.setup()
        activity = Robolectric.setupActivity(FragmentActivity::class.java)
        viewModel = ViewModelProviders.of(activity, viewModelFactory)[DetailActivityViewModel::class.java]
    }


    @Test
    fun checkHomeTeam(){
        assertEquals(null, viewModel.getListHomeTeam().value, "home team still null")
        viewModel.getHomeTeams(homeID)
        assertNotEquals(null, viewModel.getListHomeTeam().value, "home team should be not null")
        assertEquals(null, viewModel.getListAwayTeam().value, "away team shoule be null")
        assertEquals(null, viewModel.getListTeamError().value, "error shoule be null")
        assertNotEquals("", viewModel.getListHomeTeam().value!!.logo, "home team logo should not be null")
    }

    @Test
    fun checkAwayTeam(){
        assertEquals(null, viewModel.getListAwayTeam().value, "away team still null")
        viewModel.getAwayTeams(awayID)
        assertNotEquals(null, viewModel.getListAwayTeam().value, "away team should be not null")
        assertEquals(null, viewModel.getListHomeTeam().value, "home team shoule be null")
        assertEquals(null, viewModel.getListTeamError().value, "error shoule be null")
        assertNotEquals("", viewModel.getListAwayTeam().value!!.logo, "away team logo should not be null")
    }

    @Test
    @Throws(Exception::class)
    fun checkFavorite(){
        assertEquals(null, viewModel.getEventById().value, "favorite event should be null")
        viewModel.checkEvent(eventId)
        assertEquals(null, viewModel.getEventById().value, "favorite event should be null since db is droped on test")
    }
}
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
import java.net.HttpURLConnection
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@RunWith(RobolectricTestRunner::class)
class TeamViewModelTest: BaseTest(){

    @Rule
    @JvmField
    val rxTestRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var activity: FragmentActivity
    lateinit var viewModel:TeamViewModel
    val  homeID = "133604"
    val  awayID = "133602"
    val teamID = "133604"

    override fun isMockServerEnabled() = true

    @Before
    override fun setup(){
        super.setup()
        activity = Robolectric.setupActivity(FragmentActivity::class.java)
        viewModel = ViewModelProviders.of(activity, viewModelFactory)[TeamViewModel::class.java]
    }


    @Test
    fun checkHomeTeam(){
        mockHttpResponse("home.json", HttpURLConnection.HTTP_OK)
        assertEquals(null, viewModel.getListHomeTeam().value, "home team still null")
        viewModel.getHomeTeams(homeID)
        assertNotEquals(null, viewModel.getListHomeTeam().value, "home team should be not null")
        assertEquals(null, viewModel.getListAwayTeam().value, "away team shoule be null")
        assertEquals(null, viewModel.getListTeamError().value, "error shoule be null")
        assertNotEquals("", viewModel.getListHomeTeam().value!!.teamLogo, "home team logo should not be null")
    }

    @Test
    fun checkAwayTeam(){
        mockHttpResponse("away.json", HttpURLConnection.HTTP_OK)
        assertEquals(null, viewModel.getListAwayTeam().value, "away team still null")
        viewModel.getAwayTeams(awayID)
        assertNotEquals(null, viewModel.getListAwayTeam().value, "away team should be not null")
        assertEquals(null, viewModel.getListHomeTeam().value, "home team shoule be null")
        assertEquals(null, viewModel.getListTeamError().value, "error shoule be null")
        assertNotEquals("", viewModel.getListAwayTeam().value!!.teamLogo, "away team logo should not be null")
    }

    @Test
    @Throws(Exception::class)
    fun checkFavorite(){
        assertEquals(null, viewModel.getAllTeam().value, "favorite event should be null")
        viewModel.checkTeam(teamID)
        assertEquals(null, viewModel.getAllTeam().value, "favorite event should be null since db is droped on test")
    }
}
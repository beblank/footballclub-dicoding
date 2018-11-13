package com.adit.footballclub.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.adit.footballclub.BaseTest
import com.adit.footballclub.utils.Const
import com.adit.footballclub.utils.RxImmediateSchedulerRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import java.net.HttpURLConnection
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@RunWith(RobolectricTestRunner::class)
class PlayerViewModelTest: BaseTest() {

    @Rule
    @JvmField
    val rxTestRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var activity:FragmentActivity
    lateinit var viewModel:PlayerViewModel
    override fun isMockServerEnabled() = true
    val teamName = "Arsenal"

    @Before
    override fun setup(){
        super.setup()
        activity = Robolectric.setupActivity(FragmentActivity::class.java)
        viewModel = ViewModelProviders.of(activity, viewModelFactory)[PlayerViewModel::class.java]

    }

    @Test
    fun checkPlayersByTeamName(){
        mockHttpResponse("players.json", HttpURLConnection.HTTP_OK)
        assertEquals(null, viewModel.getAllPlayer().value, "check if players still null")
        viewModel.getAllPlayerFromApi(teamName)
        assertNotEquals(null, viewModel.getAllPlayer().value, "players should be not null")
        assertEquals(null, viewModel.getPlayerError().value, "error shoule be null")
        assertNotEquals(0, viewModel.getAllPlayer().value!!.size, "event list size should be > 0")
        assertNotEquals("", viewModel.getAllPlayer().value!![0].playerName, "playername should be not empty string")
    }

}
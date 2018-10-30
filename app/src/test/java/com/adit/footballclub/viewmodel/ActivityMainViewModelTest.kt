package com.adit.footballclub.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.adit.footballclub.BaseTest
import com.adit.footballclub.entity.ListEvent
import com.adit.footballclub.utils.ApiUtils
import com.adit.footballclub.utils.Const
import com.adit.footballclub.utils.POST_MOCK_PATH
import com.adit.footballclub.utils.RxImmediateSchedulerRule
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@RunWith(RobolectricTestRunner::class)
class ActivityMainViewModelTest:BaseTest(){

    @Rule
    @JvmField
    val rxTestRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var activity:FragmentActivity
    lateinit var viewModel:ActivityMainViewModel

    @Before
    override fun setup(){
        super.setup()
        activity = Robolectric.setupActivity(FragmentActivity::class.java)
        viewModel = ViewModelProviders.of(activity, viewModelFactory)[ActivityMainViewModel::class.java]
    }


    @Test
    fun checkPastEventMatchSize(){
        assertEquals(null, viewModel.getListEvents().value, "check if event still null")
        viewModel.getEventsfromApi(Const.lastMatchTab)
        assertNotEquals(null, viewModel.getListEvents().value, "event should be not null")
        assertEquals(null, viewModel.getListEventsError().value, "error shoule be null")
        assertEquals(15, viewModel.getListEvents().value!!.size, "event list size should be 15")
    }

    fun testDataObservable():Observable<ListEvent>{
        return Observable.just(ApiUtils.getUrl(POST_MOCK_PATH))
    }
}
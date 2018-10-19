package com.adit.footballclub.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import javax.inject.Inject

class ActivityMainViewModel @Inject constructor(
        val activityMainViewModelFactory: ActivityMainViewModelFactory):ViewModel(){
    private val selectedTab:MutableLiveData<Int> = MutableLiveData()

    init {
        selectedTab.value = 0
    }

    fun getSelectedTab() = selectedTab
}
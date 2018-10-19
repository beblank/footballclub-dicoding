package com.adit.footballclub.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.adit.footballclub.Utils.Const
import com.adit.footballclub.entity.repository.MatchRepostory
import javax.inject.Inject

class ActivityMainViewModel @Inject constructor(
    val matchRepostory: MatchRepostory):ViewModel(){
    private val selectedTab:MutableLiveData<Int> = MutableLiveData()

    init {
        selectedTab.value = Const.lastMatchTab
    }
    fun getSelectedTab() = selectedTab
}
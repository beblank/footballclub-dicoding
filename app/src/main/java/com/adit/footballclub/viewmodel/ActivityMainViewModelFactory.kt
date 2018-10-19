package com.adit.footballclub.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

class ActivityMainViewModelFactory @Inject constructor(
        private val activityMainViewModel: ActivityMainViewModel): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActivityMainViewModel::class.java)){
            return activityMainViewModel as T
        }
        throw IllegalAccessException("Unknown class name")
    }

}
package com.adit.footballclub.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

class EventsViewModelFactory @Inject constructor(
        private val eventsViewModel: EventsViewModel): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventsViewModel::class.java)){
            return eventsViewModel as T
        }
        throw IllegalAccessException("Unknown class name")
    }

}
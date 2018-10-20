package com.adit.footballclub.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

class DetailActivityViewModelFactory @Inject constructor(
        private val detailActivityViewModel: DetailActivityViewModel): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailActivityViewModel::class.java)) {
            return detailActivityViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
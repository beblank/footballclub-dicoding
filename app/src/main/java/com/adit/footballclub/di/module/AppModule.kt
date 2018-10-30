package com.adit.footballclub.di.module

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import com.adit.footballclub.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app:Application){
    @Provides
    @Singleton
    fun provideApp():Application = app

    @Provides
    @Singleton
    fun provideViewModelFactory(factory:ViewModelFactory):ViewModelProvider.Factory = factory


}
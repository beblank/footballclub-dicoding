package com.adit.footballclub.di

import com.adit.footballclub.BaseTest
import com.adit.footballclub.di.module.LocalModule
import com.adit.footballclub.di.module.NetModule
import com.adit.footballclub.di.module.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [LocalModule::class, NetModule::class, ViewModelModule::class])
interface TestAppComponent{
    fun inject(baseTest: BaseTest)
}
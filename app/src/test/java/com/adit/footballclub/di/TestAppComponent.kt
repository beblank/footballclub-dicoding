package com.adit.footballclub.di

import com.adit.footballclub.BaseTest
import com.adit.footballclub.di.module.NetModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class])
interface TestAppComponent{
    fun inject(baseTest: BaseTest)
}
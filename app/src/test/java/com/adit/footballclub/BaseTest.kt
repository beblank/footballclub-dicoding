package com.adit.footballclub

import com.adit.footballclub.di.DaggerTestAppComponent
import com.adit.footballclub.di.TestAppComponent
import com.adit.footballclub.di.component.DaggerAppComponent
import com.adit.footballclub.di.module.NetModule
import com.adit.footballclub.utils.Const
import com.adit.footballclub.viewmodel.ViewModelFactory
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

abstract class BaseTest{
    lateinit var testAppComponent: TestAppComponent
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Before
    open fun setup(){
        configureDi()
    }

    open fun configureDi() {
        testAppComponent = DaggerTestAppComponent.builder()
                .netModule(NetModule(Const.endpoint))
                .build()
        testAppComponent.inject(this)
    }
}


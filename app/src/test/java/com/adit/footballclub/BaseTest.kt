package com.adit.footballclub

import com.adit.footballclub.di.AppModuleTest
import com.adit.footballclub.di.BaseApplication
import com.adit.footballclub.di.DaggerTestAppComponent
import com.adit.footballclub.di.TestAppComponent
import com.adit.footballclub.di.module.AppModule
import com.adit.footballclub.di.module.NetModule
import com.adit.footballclub.utils.Const
import com.adit.footballclub.viewmodel.ViewModelFactory
import org.junit.Before
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
                .appModuleTest(AppModuleTest(BaseApplication()))
                .netModule(NetModule(Const.endpoint))
                .build()
        testAppComponent.inject(this)
    }
}


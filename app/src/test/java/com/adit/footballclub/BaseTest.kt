package com.adit.footballclub

import com.adit.footballclub.di.AppModuleTest
import com.adit.footballclub.di.BaseApplication
import com.adit.footballclub.di.DaggerTestAppComponent
import com.adit.footballclub.di.TestAppComponent
import com.adit.footballclub.di.module.NetModule
import com.adit.footballclub.utils.Const
import com.adit.footballclub.viewmodel.ViewModelFactory
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import org.junit.Before
import java.io.File
import javax.inject.Inject
import kotlin.test.AfterTest

abstract class BaseTest{
    lateinit var testAppComponent: TestAppComponent
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var mockServer:MockWebServer

    @Before
    open fun setup(){
        configureMockServer()
        configureDi()
    }

    @AfterTest
    open fun tearDown(){
        this.stopMockServer()
    }

    open fun configureDi() {
        testAppComponent = DaggerTestAppComponent.builder()
                .appModuleTest(AppModuleTest(BaseApplication()))
                .netModule(NetModule(if (isMockServerEnabled()) mockServer.url("/").toString() else Const.endpoint))
                .build()
        testAppComponent.inject(this)
    }

    abstract fun isMockServerEnabled():Boolean

    open fun configureMockServer(){
        if (isMockServerEnabled()){
            mockServer = MockWebServer()
            mockServer.start()
        }
    }

    open fun stopMockServer() {
        if (isMockServerEnabled()){
            mockServer.shutdown()
        }
    }

    open fun mockHttpResponse(fileName: String, responseCode: Int) = mockServer.enqueue(MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJson(fileName)))

    private fun getJson(path : String) : String {
        val uri = this.javaClass.classLoader.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}


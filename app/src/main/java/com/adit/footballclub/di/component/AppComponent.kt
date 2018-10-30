package com.adit.footballclub.di.component

import com.adit.footballclub.di.BaseApplication
import com.adit.footballclub.di.module.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
    AppModule::class,
    BuildersModule::class,
    NetModule::class,
    ViewModelModule::class])
interface AppComponent {
    fun inject(app:BaseApplication)
}
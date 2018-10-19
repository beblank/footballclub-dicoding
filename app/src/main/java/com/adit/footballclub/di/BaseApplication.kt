package com.adit.footballclub.di

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.adit.footballclub.Utils.Const
import com.adit.footballclub.di.component.DaggerAppComponent
import com.adit.footballclub.di.module.AppModule
import com.adit.footballclub.di.module.NetModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class BaseApplication:Application(), HasActivityInjector, HasSupportFragmentInjector{


    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject lateinit var fragmentInjector:DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule(Const.endpoint))
                .build().inject(this )

    }
}
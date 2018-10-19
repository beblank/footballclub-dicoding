package com.adit.footballclub.di.module

import com.adit.footballclub.LastMatchFragment
import com.adit.footballclub.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule{
    @ContributesAndroidInjector
    abstract fun contributeMainActivity():MainActivity

    @ContributesAndroidInjector
    abstract fun contributeLastMatchFragment():LastMatchFragment
}


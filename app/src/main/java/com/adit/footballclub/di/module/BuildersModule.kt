package com.adit.footballclub.di.module

import com.adit.footballclub.ui.fragment.NextMatchFragment
import com.adit.footballclub.ui.fragment.PrevMatchFragment
import com.adit.footballclub.ui.activity.DetailActivity
import com.adit.footballclub.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule{
    @ContributesAndroidInjector
    abstract fun contributeMainActivity():MainActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity():DetailActivity

    @ContributesAndroidInjector
    abstract fun contributePrevLastMatchFragment(): PrevMatchFragment

    @ContributesAndroidInjector
    abstract fun contributeNextLastMatchFragment(): NextMatchFragment
}


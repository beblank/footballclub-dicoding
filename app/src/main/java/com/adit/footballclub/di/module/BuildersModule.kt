package com.adit.footballclub.di.module

import com.adit.footballclub.ui.fragment.NextMatchFragment
import com.adit.footballclub.ui.activity.DetailActivity
import com.adit.footballclub.ui.activity.MainActivity
import com.adit.footballclub.ui.fragment.ContainerEventFragment
import com.adit.footballclub.ui.fragment.EventFragment
import com.adit.footballclub.ui.fragment.FavoriteFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule{
    @ContributesAndroidInjector
    abstract fun contributeMainActivity():MainActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity():DetailActivity

    @ContributesAndroidInjector
    abstract fun contributeContainerEventMatchFragment(): ContainerEventFragment

    @ContributesAndroidInjector
    abstract fun contributeEventMatchFragment(): EventFragment

    @ContributesAndroidInjector
    abstract fun contributeNextLastMatchFragment(): NextMatchFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteFragment(): FavoriteFragment
}


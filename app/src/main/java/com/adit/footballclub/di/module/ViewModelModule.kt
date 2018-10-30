package com.adit.footballclub.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.adit.footballclub.viewmodel.ActivityMainViewModel
import com.adit.footballclub.viewmodel.DetailActivityViewModel
import com.adit.footballclub.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ActivityMainViewModel::class)
    internal abstract fun mainActivityViewModel(viewModel: ActivityMainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailActivityViewModel::class)
    internal abstract fun detailActivityViewModel(viewModel: DetailActivityViewModel): ViewModel
}
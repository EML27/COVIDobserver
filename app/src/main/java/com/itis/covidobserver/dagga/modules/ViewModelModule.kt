package com.itis.covidobserver.dagga.modules

import androidx.lifecycle.ViewModelProvider
import com.itis.covidobserver.viewmodel.MyViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(
        factory: MyViewModelFactory
    ): ViewModelProvider.Factory
}

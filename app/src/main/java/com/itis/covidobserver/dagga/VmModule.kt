package com.itis.covidobserver.dagga

import com.itis.covidobserver.viewmodel.ViewModelFactoryImpl
import dagger.Module
import dagger.Provides

//not yet
@Module
class VmModule {
    @Provides
    fun provideViewModelFactory(): ViewModelFactoryImpl {
        return ViewModelFactoryImpl()
    }
}
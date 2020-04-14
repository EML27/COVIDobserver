package com.itis.covidobserver.dagga.modules

import com.itis.covidobserver.net.COVIDService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun bindCOVIDService(retrofit: Retrofit): COVIDService = retrofit.create(
        COVIDService::class.java
    )
}
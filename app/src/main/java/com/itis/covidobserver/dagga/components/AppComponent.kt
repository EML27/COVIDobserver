package com.itis.covidobserver.dagga.components

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.itis.covidobserver.dagga.modules.AppModule
import com.itis.covidobserver.dagga.modules.NetModule
import com.itis.covidobserver.dagga.modules.ServiceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class, ServiceModule::class])
interface AppComponent {

    fun getContext(): Context

    fun inject(activity: AppCompatActivity)
}
package com.itis.covidobserver

import android.app.Application
import com.itis.covidobserver.dagga.components.AppComponent
import com.itis.covidobserver.dagga.components.DaggerAppComponent
import com.itis.covidobserver.dagga.modules.AppModule
import com.itis.covidobserver.dagga.modules.NetModule
import com.itis.covidobserver.dagga.modules.ServiceModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule())
            .serviceModule(ServiceModule())
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}
package com.itis.covidobserver.viewmodel

import com.itis.covidobserver.dagga.VmModule
import com.itis.covidobserver.view.SuperActivity
import dagger.Component

@Component(modules = [VmModule::class])
interface ViewModelFactory {
    fun inject(activity: SuperActivity<*>)
}

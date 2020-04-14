package com.itis.covidobserver.view

import androidx.appcompat.app.AppCompatActivity
import com.itis.covidobserver.viewmodel.SuperViewModel
import com.itis.covidobserver.viewmodel.ViewModelFactoryImpl

abstract class SuperActivity<T : SuperViewModel> : AppCompatActivity() {
    abstract fun getViewModel(): SuperViewModel

    //TODO: inject by dagger
    var viewModelFactory =  ViewModelFactoryImpl()
}
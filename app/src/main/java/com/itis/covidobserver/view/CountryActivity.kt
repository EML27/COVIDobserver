package com.itis.covidobserver.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.itis.covidobserver.R
import com.itis.covidobserver.viewmodel.CountryViewModel
import com.itis.covidobserver.viewmodel.SuperViewModel

class CountryActivity : SuperActivity<SuperViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
    }

    override fun getViewModel() =
        ViewModelProvider(this, viewModelFactory).get(CountryViewModel::class.java)
}

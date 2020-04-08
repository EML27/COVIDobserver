package com.itis.covidobserver.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ViewModelFactoryImpl : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel() as T
                    ?: throw IllegalArgumentException("Unknown ViewModel class")
            }
            modelClass.isAssignableFrom(CountryViewModel::class.java) -> {
                CountryViewModel() as T
                    ?: throw IllegalArgumentException("Unknown ViewModel class")
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
}
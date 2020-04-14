package com.itis.covidobserver.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable

abstract class SuperViewModel : ViewModel() {

    var disposable: Disposable? = null

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

}
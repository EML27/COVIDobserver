package com.itis.covidobserver.viewmodel

import androidx.lifecycle.ViewModel
import com.itis.covidobserver.model.ApiInteractorImpl
import io.reactivex.disposables.Disposable

abstract class SuperViewModel : ViewModel() {

    var disposable: Disposable? = null

    var interactor = ApiInteractorImpl()

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

}
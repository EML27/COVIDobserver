package com.itis.covidobserver.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itis.covidobserver.net.responses.CountryResponse
import com.itis.covidobserver.net.responses.Response
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CountryViewModel : SuperViewModel(){

    fun getCountryResponse(query: String): LiveData<Response<CountryResponse>> {
        mutCountryResponse = MutableLiveData()
        disposable = interactor
            .getCountryInfo(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mutCountryResponse.value = Response.success(it)
            }, { mutCountryResponse.value = Response.error(it) })
        return mutCountryResponse
    }

    private var mutCountryResponse = MutableLiveData<Response<CountryResponse>>()
}
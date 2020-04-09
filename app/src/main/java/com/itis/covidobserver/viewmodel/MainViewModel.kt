package com.itis.covidobserver.viewmodel

//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
//import io.reactivex.rxjava3.core.Scheduler
//import io.reactivex.rxjava3.core.Single
//import io.reactivex.rxjava3.schedulers.Schedulers
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itis.covidobserver.net.responses.CountryResponse
import com.itis.covidobserver.net.responses.Response
import com.itis.covidobserver.net.responses.WorldResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel : SuperViewModel() {



    fun getQueryResponse(query: String): LiveData<Response<CountryResponse>> {
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
    private var mutInfo = MutableLiveData<Response<WorldResponse>>()

    fun getWorldStats(): LiveData<Response<WorldResponse>> {
        mutInfo = MutableLiveData()
        disposable = interactor
            .getWorldInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { mutInfo.value = Response.success(it) }, {
                    mutInfo.value = Response.error(it)
                }
            )
        return mutInfo
    }
}

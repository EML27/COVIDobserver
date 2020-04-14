package com.itis.covidobserver.model

import com.itis.covidobserver.net.COVIDService
import com.itis.covidobserver.net.responses.CountryResponse
import com.itis.covidobserver.net.responses.WorldResponse
import io.reactivex.Single
import javax.inject.Inject


class ApiInteractorImpl @Inject constructor( private val api: COVIDService)  :
    ApiInteractor {


    override fun getWorldInfo(): Single<WorldResponse> {
        return api.worldwide()
    }

    override fun getCountryInfo(query: String): Single<CountryResponse> {
        return api.country(query)
    }
}
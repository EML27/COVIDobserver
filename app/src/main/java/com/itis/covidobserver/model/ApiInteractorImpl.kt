package com.itis.covidobserver.model

import com.itis.covidobserver.net.ApiFactory
import com.itis.covidobserver.net.COVIDService
import com.itis.covidobserver.net.responses.CountryResponse
import com.itis.covidobserver.net.responses.WorldResponse
import dagger.Component
import io.reactivex.Single

@Component
class ApiInteractorImpl constructor(private val api: COVIDService = ApiFactory.covidService) :
    ApiInteractor {


    override fun getWorldInfo(): Single<WorldResponse> {
        return api.worldwide()
    }

    override fun getCountryInfo(query: String): Single<CountryResponse> {
        return api.country(query)
    }
}
package com.itis.covidobserver.model

import com.itis.covidobserver.net.responses.CountryResponse
import com.itis.covidobserver.net.responses.WorldResponse
import io.reactivex.Single


interface ApiInteractor {
    fun getWorldInfo(): Single<WorldResponse>

    fun getCountryInfo(query: String): Single<CountryResponse>
}
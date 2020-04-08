package com.itis.covidobserver.net

import com.itis.covidobserver.net.responses.CountryResponse
import com.itis.covidobserver.net.responses.WorldResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface COVIDService {

    @GET("all")
    fun worldwide(): Single<WorldResponse>

    @GET("countries/{name}")
    fun country(@Path("name") name: String): Single<CountryResponse>
}
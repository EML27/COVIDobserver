package com.itis.covidobserver.net

import com.itis.covidobserver.BuildConfig
import dagger.Component
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Component
object ApiFactory {

    private val authInterceptor = Interceptor { chain ->
        // better use separate classes for Interceptors
        val newUrl = chain.request().url().newBuilder()
            .build()

        val newRequest = chain.request().newBuilder().url(newUrl).build()
        chain.proceed(newRequest)
    }

    private val client by lazy {
        OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }


    val covidService: COVIDService by lazy {
        retrofit.create(COVIDService::class.java)
    }
}

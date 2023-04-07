package com.example.mycountries.di

import com.example.mycountries.model.CountriesAPI
import com.example.mycountries.model.CountriesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class APIModule {

    private val BASE_URL ="https://raw.githubusercontent.com"

    @Provides
    fun provideCountriesAPI(): CountriesAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesAPI::class.java)
    }

    @Provides
    fun provideCountriesService(): CountriesService{
        return CountriesService()
    }
}
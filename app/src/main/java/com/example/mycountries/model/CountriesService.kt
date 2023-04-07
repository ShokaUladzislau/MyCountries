package com.example.mycountries.model


import com.example.mycountries.di.DaggerAPIComponent
import io.reactivex.Single
import javax.inject.Inject



class CountriesService {

    @Inject
    lateinit var API: CountriesAPI

    init {
        DaggerAPIComponent.create().inject(this)
    }

    fun getCountries(): Single<List<Country>> {
        return API.getCountries()
    }
}
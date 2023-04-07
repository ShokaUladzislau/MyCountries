package com.example.mycountries.di

import com.example.mycountries.model.CountriesService
import com.example.mycountries.view.CountriesFragment
import com.example.mycountries.viewmodel.CountriesViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [APIModule::class, ViewModelModule::class])
interface APIComponent {
    fun inject(service: CountriesService)
    fun inject(viewModel: CountriesViewModel)
    fun inject(countriesFragment: CountriesFragment)
}

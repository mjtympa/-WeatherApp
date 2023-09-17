package com.mujeeb.weatherapp.di.module

import com.mujeeb.weatherapp.data.repository.CityForecastRepositoryImpl
import com.mujeeb.weatherapp.domain.repository.CityForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CityForecastRepositoryModule {
    @Binds
    abstract fun bindCityForecastRepository(repositoryImpl: CityForecastRepositoryImpl) : CityForecastRepository
}
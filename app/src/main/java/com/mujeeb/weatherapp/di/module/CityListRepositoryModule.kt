package com.mujeeb.weatherapp.di.module

import com.mujeeb.weatherapp.data.repository.CityListRepositoryImpl
import com.mujeeb.weatherapp.domain.repository.CityListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CityListRepositoryModule {
    @Binds
    abstract fun bindCityListRepository(repositoryImpl: CityListRepositoryImpl) : CityListRepository
}
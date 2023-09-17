package com.mujeeb.weatherapp.di.module


import com.mujeeb.weatherapp.presentation.mapper.ForecastViewStateMapper
import com.mujeeb.weatherapp.presentation.mapper.ForecastViewStateMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ViewStateMapperModule{
    @Binds
    abstract fun providesViewStateMapper(mapperImp: ForecastViewStateMapperImpl) : ForecastViewStateMapper
}

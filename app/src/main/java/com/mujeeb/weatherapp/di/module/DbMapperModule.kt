package com.mujeeb.weatherapp.di.module

import com.mujeeb.weatherapp.data.database.mapper.DbMapper
import com.mujeeb.weatherapp.data.database.mapper.DbMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DbMapperModule{
    @Binds
    abstract fun bindDbMapper(mapperImp: DbMapperImpl) : DbMapper
}
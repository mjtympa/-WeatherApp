package com.mujeeb.weatherapp.di.module

import android.content.Context
import androidx.room.Room
import com.mujeeb.weatherapp.data.database.CityRoomDatabase
import com.mujeeb.weatherapp.data.database.dao.CityResponseDao
import com.mujeeb.weatherapp.data.database.dao.WeatherResponseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideCityResponseDao(cityRoomDatabase: CityRoomDatabase): CityResponseDao {
        return cityRoomDatabase.cityResponseDao()
    }

    @Provides
    fun provideWeatherResponseDao(cityRoomDatabase: CityRoomDatabase): WeatherResponseDao {
        return cityRoomDatabase.weatherResponseDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): CityRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            CityRoomDatabase::class.java,
            "city_database",
        ).build()
    }
}

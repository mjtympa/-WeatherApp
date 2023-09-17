package com.mujeeb.weatherapp.di.module

import android.content.Context
import androidx.room.Room
import com.mujeeb.weatherapp.data.database.ForecastRoomDatabase
import com.mujeeb.weatherapp.data.database.dao.CityListDao
import com.mujeeb.weatherapp.data.database.dao.CityForecastDao
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
    fun provideCityResponseDao(forecastRoomDatabase: ForecastRoomDatabase): CityListDao {
        return forecastRoomDatabase.cityResponseDao()
    }

    @Provides
    fun provideWeatherResponseDao(forecastRoomDatabase: ForecastRoomDatabase): CityForecastDao {
        return forecastRoomDatabase.weatherResponseDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): ForecastRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            ForecastRoomDatabase::class.java,
            "city_database",
        ).build()
    }
}

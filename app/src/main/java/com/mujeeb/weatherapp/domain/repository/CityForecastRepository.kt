package com.mujeeb.weatherapp.domain.repository

import com.mujeeb.weatherapp.domain.model.Forecast
import com.mujeeb.weatherapp.utils.DataHandler


interface CityForecastRepository {

    suspend fun getForecast(): DataHandler<Forecast>

    suspend fun insertForecast(forecast: Forecast)
    suspend fun getForecast(city: Int): DataHandler<Forecast>
}
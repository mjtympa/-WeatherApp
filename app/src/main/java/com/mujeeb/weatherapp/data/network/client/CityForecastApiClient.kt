package com.mujeeb.weatherapp.data.network.client

import com.mujeeb.weatherapp.BuildConfig
import com.mujeeb.weatherapp.data.network.model.ApiForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface CityForecastApiClient {
    @GET(BuildConfig.WEATHER_ENDPOINT)
    suspend fun getForecast(
        @Query("id") city: Int,
        @Query("units") units: String,
        @Query("appid") apikey: String,
    ): ApiForecast

}

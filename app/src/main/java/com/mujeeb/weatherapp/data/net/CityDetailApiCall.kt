package com.mujeeb.weatherapp.data.net

import com.mujeeb.weatherapp.BuildConfig
import com.mujeeb.weatherapp.data.model.city_details.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CityDetailApiCall {

    @GET(BuildConfig.WEATHER_ENDPOINT)
    suspend fun getWeather(
        @Query("id") city: Int,
        @Query("units") units: String,
        @Query("appid") apikey: String,
    ): Response
}

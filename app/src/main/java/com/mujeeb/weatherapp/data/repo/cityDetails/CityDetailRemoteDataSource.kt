package com.mujeeb.weatherapp.data.repo.cityDetails

import com.mujeeb.weatherapp.BuildConfig.API_KEY
import com.mujeeb.weatherapp.common.utils.UNITS
import com.mujeeb.weatherapp.data.model.city_details.Response
import com.mujeeb.weatherapp.data.net.CityDetailApiCall
import javax.inject.Inject

class CityDetailRemoteDataSource @Inject constructor(private val weatherApiCall: CityDetailApiCall) {
    suspend fun getWeather(city: Int): Response {
        return weatherApiCall.getWeather(city, UNITS, API_KEY)
    }
}

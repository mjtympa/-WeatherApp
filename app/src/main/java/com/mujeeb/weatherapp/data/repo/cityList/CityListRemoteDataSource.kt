package com.mujeeb.weatherapp.data.repo.cityList

import com.mujeeb.weatherapp.BuildConfig.API_KEY
import com.mujeeb.weatherapp.data.model.city_list.CityResponse
import com.mujeeb.weatherapp.data.net.CityListApiCall
import javax.inject.Inject

class CityListRemoteDataSource @Inject constructor(private val cityApiCall: CityListApiCall) {

    suspend fun getCityList(city: String): CityResponse {
        return cityApiCall.getCityList(searchItem = city, API_KEY)
    }
}

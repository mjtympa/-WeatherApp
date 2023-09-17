package com.mujeeb.weatherapp.domain.repository

import com.mujeeb.weatherapp.domain.model.CityListResponse
import com.mujeeb.weatherapp.utils.DataHandler


interface CityListRepository {

    suspend fun getCityListResponse(city: String): DataHandler<CityListResponse>
    suspend fun getCityListResponse(): DataHandler<CityListResponse>
    suspend fun insertCityListResponse(cityListResponse: CityListResponse)
}
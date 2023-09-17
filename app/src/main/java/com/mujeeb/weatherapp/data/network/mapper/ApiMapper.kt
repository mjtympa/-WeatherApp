package com.mujeeb.weatherapp.data.network.mapper


import com.mujeeb.weatherapp.data.network.model.ApiCityListResponse
import com.mujeeb.weatherapp.data.network.model.ApiForecast
import com.mujeeb.weatherapp.domain.model.CityListResponse
import com.mujeeb.weatherapp.domain.model.Forecast

interface ApiMapper {

    fun mapApiCityListResponseToDomain(apiCityListResponse: ApiCityListResponse?): CityListResponse


    fun mapApiForecastToDomain(apiForecast: ApiForecast): Forecast
}



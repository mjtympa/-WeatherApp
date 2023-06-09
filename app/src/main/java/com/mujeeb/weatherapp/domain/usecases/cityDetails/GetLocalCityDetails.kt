package com.mujeeb.weatherapp.domain.usecases.cityDetails

import com.mujeeb.weatherapp.data.model.city_details.Response
import com.mujeeb.weatherapp.data.repo.cityDetails.CityDetailLocalDataSource
import javax.inject.Inject

class GetLocalCityDetails @Inject constructor(private val localDataSource: CityDetailLocalDataSource) {
    suspend operator fun invoke(): Response {
        return localDataSource.getWeatherResponse()
    }
}

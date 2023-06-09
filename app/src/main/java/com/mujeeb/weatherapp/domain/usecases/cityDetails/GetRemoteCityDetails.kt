package com.mujeeb.weatherapp.domain.usecases.cityDetails

import com.mujeeb.weatherapp.data.model.city_details.Response
import com.mujeeb.weatherapp.data.repo.cityDetails.CityDetailRemoteDataSource
import javax.inject.Inject

class GetRemoteCityDetails @Inject constructor(private val remoteDataSource: CityDetailRemoteDataSource) {
    suspend operator fun invoke(city: Int): Response {
        return remoteDataSource.getWeather(city = city)
    }
}

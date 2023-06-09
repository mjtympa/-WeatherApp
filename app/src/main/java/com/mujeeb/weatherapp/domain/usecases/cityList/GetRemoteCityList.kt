package com.mujeeb.weatherapp.domain.usecases.cityList

import com.mujeeb.weatherapp.data.model.city_list.CityResponse
import com.mujeeb.weatherapp.data.repo.cityList.CityListRemoteDataSource
import javax.inject.Inject

class GetRemoteCityList @Inject constructor(private val remoteDataSource: CityListRemoteDataSource) {
    suspend operator fun invoke(city: String): CityResponse {
        return remoteDataSource.getCityList(city)
    }
}

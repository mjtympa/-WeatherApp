package com.mujeeb.weatherapp.domain.usecases.cityList

import com.mujeeb.weatherapp.data.model.city_list.CityResponse
import com.mujeeb.weatherapp.data.repo.cityList.CityListLocalDataSource
import javax.inject.Inject

class GetLocalCityList @Inject constructor(private val localDataSource: CityListLocalDataSource) {
    suspend operator fun invoke(): CityResponse {
        return localDataSource.getCityList()
    }
}

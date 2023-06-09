package com.mujeeb.weatherapp.data.repo.cityList

import com.mujeeb.weatherapp.data.database.dao.CityResponseDao
import com.mujeeb.weatherapp.data.model.city_list.CityResponse
import javax.inject.Inject

class CityListLocalDataSource @Inject constructor(private val cityResponseDao: CityResponseDao) {

    suspend fun getCityList(): CityResponse {
        return cityResponseDao.getCityResponse()
    }

    suspend fun insertCityList(cityResponse: CityResponse) {
        cityResponseDao.insert(cityResponse)
    }
}

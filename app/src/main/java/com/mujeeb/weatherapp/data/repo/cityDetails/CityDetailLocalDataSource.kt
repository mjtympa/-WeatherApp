package com.mujeeb.weatherapp.data.repo.cityDetails

import com.mujeeb.weatherapp.data.database.dao.WeatherResponseDao
import com.mujeeb.weatherapp.data.model.city_details.Response
import javax.inject.Inject

class CityDetailLocalDataSource @Inject constructor(private val weatherResponseDao: WeatherResponseDao) {

    suspend fun getWeatherResponse(): Response {
        return weatherResponseDao.getWeatherResponse()
    }

    suspend fun inserttWeatherResponse(response: Response) {
        weatherResponseDao.insert(response)
    }
}

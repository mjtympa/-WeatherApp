package com.mujeeb.weatherapp.domain.usecases.cityDetails

import com.mujeeb.weatherapp.data.model.city_details.Response
import com.mujeeb.weatherapp.data.repo.cityDetails.CityDetailLocalDataSource
import javax.inject.Inject

class AddCityDetails @Inject constructor(private val localDataSource: CityDetailLocalDataSource) {
    suspend operator fun invoke(response: Response) {
        return localDataSource.inserttWeatherResponse(response)
    }
}

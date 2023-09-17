package com.mujeeb.weatherapp.domain.usecases

import com.mujeeb.weatherapp.domain.model.Forecast
import com.mujeeb.weatherapp.domain.repository.CityForecastRepository
import javax.inject.Inject

class AddForecastUseCase @Inject constructor(private val repo: CityForecastRepository) {
    suspend operator fun invoke(forecast: Forecast) {
        repo.insertForecast(forecast)
    }
}

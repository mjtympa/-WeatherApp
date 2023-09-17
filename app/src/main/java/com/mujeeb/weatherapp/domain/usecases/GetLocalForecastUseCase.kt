package com.mujeeb.weatherapp.domain.usecases

import com.mujeeb.weatherapp.domain.model.Forecast
import com.mujeeb.weatherapp.domain.repository.CityForecastRepository
import com.mujeeb.weatherapp.utils.DataHandler
import javax.inject.Inject

class GetLocalForecastUseCase @Inject constructor(private  val repo: CityForecastRepository) {
    suspend operator fun invoke(): DataHandler<Forecast> {
        return repo.getForecast()
    }
}

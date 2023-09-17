package com.mujeeb.weatherapp.presentation.mapper

import com.mujeeb.weatherapp.domain.model.CityListResponse
import com.mujeeb.weatherapp.domain.model.Forecast
import com.mujeeb.weatherapp.presentation.viewstate.CityListResponseViewState
import com.mujeeb.weatherapp.presentation.viewstate.ForecastViewState

interface ForecastViewStateMapper {
    fun mapDomainCityListResponseToViewState(cityListResponse: CityListResponse): CityListResponseViewState

    fun mapDomainForecastToViewState(forecast: Forecast): ForecastViewState
}


package com.mujeeb.weatherapp.data.database.mapper

import com.mujeeb.weatherapp.data.database.entities.DbCityListResponse
import com.mujeeb.weatherapp.data.database.entities.DbForecast
import com.mujeeb.weatherapp.domain.model.CityListResponse
import com.mujeeb.weatherapp.domain.model.Forecast


interface DbMapper {
  fun mapDomainCityListResponseToDb(cityListResponse: CityListResponse): DbCityListResponse

  fun mapDomainForecastToDb(forecast: Forecast): DbForecast

  fun mapDbForecastToDomain(dbForecast: DbForecast): Forecast
  fun mapDbCityListResponseToDomain(dbCityListResponse: DbCityListResponse): CityListResponse
}
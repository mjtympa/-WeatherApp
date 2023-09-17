package com.mujeeb.weatherapp.data.database.mapper

import com.mujeeb.weatherapp.data.database.entities.DbCityListResponse
import com.mujeeb.weatherapp.data.database.entities.DbForecast
import com.mujeeb.weatherapp.domain.model.CityListResponse
import com.mujeeb.weatherapp.domain.model.Forecast
import javax.inject.Inject


class DbMapperImpl @Inject constructor() : DbMapper {
    override fun mapDomainCityListResponseToDb(cityListResponse: CityListResponse): DbCityListResponse {
        return with(cityListResponse) {
            DbCityListResponse(
                list = list
            )
        }
    }

    override fun mapDbCityListResponseToDomain(dbCityListResponse: DbCityListResponse) =
        with(dbCityListResponse) {
            CityListResponse(
                list = list
            )
        }

    override fun mapDomainForecastToDb(forecast: Forecast): DbForecast {
        return with(forecast){
            DbForecast(
                list = list,
                city = city
            )
        }
    }

    override fun mapDbForecastToDomain(dbForecast: DbForecast) = with(dbForecast){
        Forecast(
            list = list,
            city = city
        )
    }


}

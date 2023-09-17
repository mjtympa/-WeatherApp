package com.mujeeb.weatherapp.data.repository

import com.mujeeb.weatherapp.BuildConfig
import com.mujeeb.weatherapp.data.database.dao.CityForecastDao
import com.mujeeb.weatherapp.data.database.mapper.DbMapper
import com.mujeeb.weatherapp.data.enums.ErrorModel
import com.mujeeb.weatherapp.data.enums.ErrorType
import com.mujeeb.weatherapp.data.network.client.CityForecastApiClient
import com.mujeeb.weatherapp.data.network.mapper.ApiMapper
import com.mujeeb.weatherapp.domain.model.Forecast
import com.mujeeb.weatherapp.domain.repository.CityForecastRepository
import com.mujeeb.weatherapp.utils.DataHandler
import com.mujeeb.weatherapp.utils.UNITS
import javax.inject.Inject

class CityForecastRepositoryImpl @Inject constructor(
    private val dao: CityForecastDao,
    private val dbMapper: DbMapper,
    private val api: CityForecastApiClient,
    private val apiMapper: ApiMapper
) : CityForecastRepository {
    override suspend fun getForecast(): DataHandler<Forecast> {
        return try {
            DataHandler.SUCCESS(dbMapper.mapDbForecastToDomain(dao.getForecast()))
        } catch (ex: Exception) {
            DataHandler.ERROR(error = ErrorModel(ErrorType.NETWORK))
        }
    }

    override suspend fun insertForecast(forecast: Forecast) {
        dao.insert(dbMapper.mapDomainForecastToDb(forecast))
    }

    override suspend fun getForecast(city: Int): DataHandler<Forecast> {
        return try {
            DataHandler.SUCCESS(
                apiMapper.mapApiForecastToDomain(
                    api.getForecast(
                        city,
                        UNITS,
                        BuildConfig.API_KEY
                    )
                )
            )
        } catch (ex: Exception) {
            DataHandler.ERROR(error = ErrorModel(ErrorType.NETWORK))
        }

    }
}
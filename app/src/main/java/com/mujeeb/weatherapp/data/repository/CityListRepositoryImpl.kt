package com.mujeeb.weatherapp.data.repository

import com.mujeeb.weatherapp.BuildConfig
import com.mujeeb.weatherapp.data.database.dao.CityListDao
import com.mujeeb.weatherapp.data.database.mapper.DbMapper
import com.mujeeb.weatherapp.data.enums.ErrorModel
import com.mujeeb.weatherapp.data.enums.ErrorType
import com.mujeeb.weatherapp.data.network.client.CityListApiClient
import com.mujeeb.weatherapp.data.network.mapper.ApiMapper
import com.mujeeb.weatherapp.domain.model.CityListResponse
import com.mujeeb.weatherapp.domain.repository.CityListRepository
import com.mujeeb.weatherapp.utils.DataHandler
import com.mujeeb.weatherapp.utils.mapErrorModel
import com.mujeeb.weatherapp.utils.mapErrorResponse
import javax.inject.Inject

class CityListRepositoryImpl @Inject constructor(
    private val dao: CityListDao,
    private val dbMapper: DbMapper,
    private val cityListApiClient: CityListApiClient,
    private val apiMapper: ApiMapper
) : CityListRepository {
    override suspend fun getCityListResponse(city: String): DataHandler<CityListResponse> {
        return try {
            val response = cityListApiClient.getCityListResponse(
                searchItem = city,
                BuildConfig.API_KEY
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    DataHandler.SUCCESS(apiMapper.mapApiCityListResponseToDomain(it))
                } ?: DataHandler.ERROR(error = ErrorModel(ErrorType.UNKNOWN))

            } else {
                DataHandler.ERROR(error = response.mapErrorResponse().mapErrorModel())
            }
        } catch (ex: Exception) {
            DataHandler.ERROR(error = ErrorModel(ErrorType.NETWORK))
        }
    }

    override suspend fun getCityListResponse(): DataHandler<CityListResponse> {
        return try {
            DataHandler.SUCCESS(dbMapper.mapDbCityListResponseToDomain(dao.getCityList()))
        } catch (ex: Exception) {
            DataHandler.ERROR(error = ErrorModel(ErrorType.NETWORK))
        }
    }

    override suspend fun insertCityListResponse(cityListResponse: CityListResponse) {
            dao.insert(dbMapper.mapDomainCityListResponseToDb(cityListResponse))
    }
}
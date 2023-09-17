package com.mujeeb.weatherapp.data.network.client

import com.mujeeb.weatherapp.BuildConfig
import com.mujeeb.weatherapp.data.network.model.ApiCityListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CityListApiClient {

    @GET(BuildConfig.CITY_LIST_ENDPOINT)
    suspend fun getCityListResponse(
        @Query("q") searchItem: String,
        @Query("appid") apikey: String,
    ): Response<ApiCityListResponse>
}

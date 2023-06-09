package com.mujeeb.weatherapp.data.net

import com.mujeeb.weatherapp.BuildConfig
import com.mujeeb.weatherapp.data.model.city_list.CityResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CityListApiCall {

    @GET(BuildConfig.CITY_LIST_ENDPOINT)
    suspend fun getCityList(
        @Query("q") searchItem: String,
        @Query("appid") apikey: String,
    ): CityResponse
}

package com.mujeeb.weatherapp.common.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mujeeb.weatherapp.data.model.city_details.City
import com.mujeeb.weatherapp.data.model.city_details.Result

class CityDetailsConverters {
    @TypeConverter
    fun fromCityDetail(value: List<Result>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Result>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCityDetail(value: String): List<Result> {
        val gson = Gson()
        val type = object : TypeToken<List<Result>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun cityToString(city: City): String = Gson().toJson(city)

    @TypeConverter
    fun stringToCity(string: String): City = Gson().fromJson(string, City::class.java)
}

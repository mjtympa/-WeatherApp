package com.mujeeb.weatherapp.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mujeeb.weatherapp.domain.model.City
import com.mujeeb.weatherapp.domain.model.ForecastResult
import com.mujeeb.weatherapp.domain.model.WeatherResult

class Converters {
    @TypeConverter
    fun weatherResultListToString(value: List<WeatherResult>): String {
        val gson = Gson()
        val type = object : TypeToken<List<WeatherResult>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun stringToWeatherResultList(value: String): List<WeatherResult> {
        val gson = Gson()
        val type = object : TypeToken<List<WeatherResult>>() {}.type
        return gson.fromJson(value, type)
    }


    @TypeConverter
    fun stringToForecastResult(value: String): List<ForecastResult> {
        val gson = Gson()
        val type = object : TypeToken<List<ForecastResult>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun forecastResultToString(value: List<ForecastResult>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ForecastResult>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun cityToString(city: City): String = Gson().toJson(city)

    @TypeConverter
    fun stringToCity(string: String): City = Gson().fromJson(string, City::class.java)
}

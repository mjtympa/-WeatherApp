package com.mujeeb.weatherapp.common.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mujeeb.weatherapp.data.model.city_list.Result

class CityListConverters {
    @TypeConverter
    fun fromCityList(value: List<Result>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Result>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCityList(value: String): List<Result> {
        val gson = Gson()
        val type = object : TypeToken<List<Result>>() {}.type
        return gson.fromJson(value, type)
    }
}

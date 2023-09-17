package com.mujeeb.weatherapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mujeeb.weatherapp.domain.model.City
import com.mujeeb.weatherapp.domain.model.ForecastResult
import com.mujeeb.weatherapp.domain.model.WeatherResult

@Entity(tableName = "city_table")
data class DbCityListResponse(
    @PrimaryKey(autoGenerate = true) val id: Int = -1,
    var message: String? = null,
    var cod: String? = null,
    var count: Int? = null,
    var list: List<WeatherResult>? = null
)

@Entity(tableName = "weather_table")
data class DbForecast(
    @PrimaryKey(autoGenerate = true) val id: Int = -1,
    var cod: String? = null,
    var message: Double? = null,
    var cnt: Int? = null,
    var list: List<ForecastResult>? = null,
    var city: City? = null,
    )


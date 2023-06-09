package com.mujeeb.weatherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mujeeb.weatherapp.common.utils.CityDetailsConverters
import com.mujeeb.weatherapp.common.utils.CityListConverters
import com.mujeeb.weatherapp.data.database.dao.CityResponseDao
import com.mujeeb.weatherapp.data.database.dao.WeatherResponseDao
import com.mujeeb.weatherapp.data.model.city_details.Response
import com.mujeeb.weatherapp.data.model.city_list.CityResponse

@Database(entities = [CityResponse::class, Response::class], version = 1, exportSchema = false)
@TypeConverters(CityListConverters::class, CityDetailsConverters::class)
abstract class CityRoomDatabase : RoomDatabase() {
    abstract fun cityResponseDao(): CityResponseDao
    abstract fun weatherResponseDao(): WeatherResponseDao
}

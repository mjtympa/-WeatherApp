package com.mujeeb.weatherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mujeeb.weatherapp.utils.Converters
import com.mujeeb.weatherapp.data.database.dao.CityListDao
import com.mujeeb.weatherapp.data.database.dao.CityForecastDao
import com.mujeeb.weatherapp.data.database.entities.DbForecast
import com.mujeeb.weatherapp.data.database.entities.DbCityListResponse

@Database(entities = [DbCityListResponse::class, DbForecast::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ForecastRoomDatabase : RoomDatabase() {
    abstract fun cityResponseDao(): CityListDao
    abstract fun weatherResponseDao(): CityForecastDao
}

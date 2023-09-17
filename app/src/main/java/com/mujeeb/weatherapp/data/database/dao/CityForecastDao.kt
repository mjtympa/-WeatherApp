package com.mujeeb.weatherapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mujeeb.weatherapp.data.database.entities.DbForecast

@Dao
interface CityForecastDao {
    @Query("SELECT * FROM weather_table")
    suspend fun getForecast(): DbForecast

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(forecast: DbForecast)

    @Query("DELETE FROM weather_table")
    suspend fun deleteAll()
}

package com.mujeeb.weatherapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mujeeb.weatherapp.data.model.city_details.Response

@Dao
interface WeatherResponseDao {
    @Query("SELECT * FROM weather_table")
    suspend fun getWeatherResponse(): Response

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(response: Response)

    @Query("DELETE FROM weather_table")
    suspend fun deleteAll()
}

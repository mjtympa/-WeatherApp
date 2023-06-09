package com.mujeeb.weatherapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mujeeb.weatherapp.data.model.city_list.CityResponse

@Dao
interface CityResponseDao {
    @Query("SELECT * FROM city_table")
    suspend fun getCityResponse(): CityResponse

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cityResponse: CityResponse)

    @Query("DELETE FROM city_table")
    suspend fun deleteAll()
}

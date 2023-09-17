package com.mujeeb.weatherapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mujeeb.weatherapp.data.database.entities.DbCityListResponse

@Dao
interface CityListDao {
    @Query("SELECT * FROM city_table")
    suspend fun getCityList(): DbCityListResponse

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cityDetails: DbCityListResponse)

    @Query("DELETE FROM city_table")
    suspend fun deleteAll()
}

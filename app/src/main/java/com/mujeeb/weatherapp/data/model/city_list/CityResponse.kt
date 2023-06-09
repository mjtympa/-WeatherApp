package com.mujeeb.weatherapp.data.model.city_list

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
@Entity(tableName = "city_table")
data class CityResponse(

    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("message")
    @Expose
    var message: String? = null,
    @SerializedName("cod")
    @Expose
    var cod: String? = null,
    @SerializedName("count")
    @Expose
    var count: Int? = null,
    @SerializedName("list")
    @Expose
    var list: List<Result>? = null,

)

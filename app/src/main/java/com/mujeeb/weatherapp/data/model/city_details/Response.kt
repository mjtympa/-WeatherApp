package com.mujeeb.weatherapp.data.model.city_details

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather_table")
data class Response(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("cod")
    @Expose
    var cod: String? = null,
    @SerializedName("message")
    @Expose
    var message: Double? = null,
    @SerializedName("cnt")
    @Expose
    var cnt: Int? = null,
    @SerializedName("list")
    @Expose
    var list: List<Result>? = null,
    @SerializedName("city")
    @Expose
    var city: City? = null,

)

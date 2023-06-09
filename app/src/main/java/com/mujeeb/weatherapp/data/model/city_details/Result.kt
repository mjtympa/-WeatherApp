package com.mujeeb.weatherapp.data.model.city_details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mujeeb.weatherapp.data.model.Clouds
import com.mujeeb.weatherapp.data.model.Main
import com.mujeeb.weatherapp.data.model.Weather
import com.mujeeb.weatherapp.data.model.Wind

class Result {

    @SerializedName("dt")
    @Expose
    var dt: Int? = null

    @SerializedName("main")
    @Expose
    var main: Main? = null

    @SerializedName("weather")
    @Expose
    var weather: List<Weather>? = null

    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null

    @SerializedName("wind")
    @Expose
    var wind: Wind? = null

    @SerializedName("rain")
    @Expose
    var rain: Rain? = null

    @SerializedName("sys")
    @Expose
    var sys: Sys? = null

    @SerializedName("dt_txt")
    @Expose
    var dtTxt: String? = null
}

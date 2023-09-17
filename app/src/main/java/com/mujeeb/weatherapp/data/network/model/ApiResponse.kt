package com.mujeeb.weatherapp.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiForecast(
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
    var list: List<ApiForecastResult>? = null,
    @SerializedName("city")
    @Expose
    var city: ApiCity? = null,
    )

class ApiCity {

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("coord")
    @Expose
    var coord: Coord? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("timezone")
    @Expose
    var timezone: Int? = null
}

class ApiForecastResult {

    @SerializedName("dt")
    @Expose
    var dt: Int? = null

    @SerializedName("main")
    @Expose
    var main: Main? = null

    @SerializedName("weather")
    @Expose
    var weather: List<ApiWeather>? = null

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

data class ApiCityListResponse(
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
    var list: List<ApiWeatherResult>? = null)


class Rain {

    @SerializedName("3h")
    @Expose
    var h: Double? = null
}


data class ApiWeatherResult(

    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("coord")
    @Expose
    var coord: Coord? = null,
    @SerializedName("main")
    @Expose
    var main: Main? = null,
    @SerializedName("dt")
    @Expose
    var dt: Int? = null,
    @SerializedName("wind")
    @Expose
    var wind: Wind? = null,
    @SerializedName("sys")
    @Expose
    var sys: ForecastSys? = null,
    @SerializedName("rain")
    @Expose
    var rain: Any? = null,
    @SerializedName("snow")
    @Expose
    var snow: Any? = null,
    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null,
    @SerializedName("weather")
    @Expose
    var weather: List<ApiWeather>? = null,

    )

class ForecastSys {

    @SerializedName("type")
    @Expose
    var type: Int? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("message")
    @Expose
    var message: Double? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("sunrise")
    @Expose
    var sunrise: Int? = null

    @SerializedName("sunset")
    @Expose
    var sunset: Int? = null
}


class Sys {
    @SerializedName("pod")
    @Expose
    var pod: String? = null
}

class Clouds {

    @SerializedName("all")
    @Expose
    var all: Int? = null
}
class Coord {

    @SerializedName("lon")
    @Expose
    var lon: Double? = null

    @SerializedName("lat")
    @Expose
    var lat: Double? = null
}


class Main {

    @SerializedName("temp")
    @Expose
    var temp: Float? = null

    @SerializedName("temp_min")
    @Expose
    var tempMin: Float? = null

    @SerializedName("temp_max")
    @Expose
    var tempMax: Float? = null

    @SerializedName("pressure")
    @Expose
    var pressure: Float? = null

    @SerializedName("sea_level")
    @Expose
    var seaLevel: Float? = null

    @SerializedName("grnd_level")
    @Expose
    var grndLevel: Float? = null

    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null

    @SerializedName("temp_kf")
    @Expose
    var tempKf: Float? = null
}
class ApiWeather {

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("main")
    @Expose
    var main: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("icon")
    @Expose
    var icon: String? = null
}
class Wind {

    @SerializedName("speed")
    @Expose
    var speed: Double? = null

    @SerializedName("deg")
    @Expose
    var deg: Double? = null
}

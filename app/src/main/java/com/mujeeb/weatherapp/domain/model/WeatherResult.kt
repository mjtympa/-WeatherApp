package com.mujeeb.weatherapp.domain.model



data class Forecast(
    var list: List<ForecastResult>? = null,
    var city: City? = null,
)

class ForecastResult (
    var dt: Int? = null,
    var main: Main? = null,
    var weather: List<Weather>? = null,
    var clouds: Clouds? = null,
    var wind: Wind? = null,
    var rain: Rain? = null,
    var sys: ForecastSys? = null,
    var dtTxt: String? = null
)

class Rain (
    var h: Double? = null
)
class ForecastSys (
    var pod: String? = null
)

class City (
    var id: Int? = null,
    var name: String? = null,
    var coord: Coord? = null,
    var country: String? = null,
    var timezone: Int? = null,
)

data class CityListResponse(
    var list: List<WeatherResult>? = null
)

data class WeatherResult(
    var id: Int? = null,
    var name: String? = null,
    var coord: Coord? = null,
    var main: Main? = null,
    var dt: Int? = null,
    var wind: Wind? = null,
    var sys: Sys? = null,
    var rain: Any? = null,
    var snow: Any? = null,
    var clouds: Clouds? = null,
    var weather: List<Weather>? = null
)

class Sys (
    var country: String? = null
)



class Clouds(
    var all: Int? = null
)

class Coord(
    var lon: Double? = null,
    var lat: Double? = null
)

class Main(
    var temp: Float? = null
)

class Weather(
    var id: Int? = null,
    var main: String? = null,
    var description: String? = null,
    var icon: String? = null
)

class Wind(
    var speed: Double? = null,
    var deg: Double? = null
)
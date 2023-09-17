package com.mujeeb.weatherapp.presentation.viewstate


data class ForecastViewState(
    var list: List<ForecastResultViewState>? = null,
    var city: CityViewState? = null,
)

class ForecastResultViewState (
    var dt: Int? = null,
    var main: Main? = null,
    var weather: List<WeatherViewState>? = null,
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

class CityViewState (
    var id: Int? = null,
    var name: String? = null,
    var coord: Coord? = null,
    var country: String? = null,
    var timezone: Int? = null,
)

data class CityListResponseViewState(
    var list: List<WeatherResultViewState>? = null
)

data class WeatherResultViewState(
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
    var weather: List<WeatherViewState>? = null
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

class WeatherViewState(
    var id: Int? = null,
    var main: String? = null,
    var description: String? = null,
    var icon: String? = null
)

class Wind(
    var speed: Double? = null,
    var deg: Double? = null
)

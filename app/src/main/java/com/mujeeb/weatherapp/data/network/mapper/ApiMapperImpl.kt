package com.mujeeb.weatherapp.data.network.mapper


import com.mujeeb.weatherapp.data.network.model.ApiCity
import com.mujeeb.weatherapp.data.network.model.ApiCityListResponse
import com.mujeeb.weatherapp.data.network.model.ApiForecast
import com.mujeeb.weatherapp.data.network.model.ApiForecastResult
import com.mujeeb.weatherapp.data.network.model.ApiWeather
import com.mujeeb.weatherapp.data.network.model.ApiWeatherResult
import com.mujeeb.weatherapp.domain.model.City
import com.mujeeb.weatherapp.domain.model.CityListResponse
import com.mujeeb.weatherapp.domain.model.Clouds
import com.mujeeb.weatherapp.domain.model.Coord
import com.mujeeb.weatherapp.domain.model.Forecast
import com.mujeeb.weatherapp.domain.model.ForecastResult
import com.mujeeb.weatherapp.domain.model.ForecastSys
import com.mujeeb.weatherapp.domain.model.Main
import com.mujeeb.weatherapp.domain.model.Rain
import com.mujeeb.weatherapp.domain.model.WeatherResult
import com.mujeeb.weatherapp.domain.model.Sys
import com.mujeeb.weatherapp.domain.model.Weather
import com.mujeeb.weatherapp.domain.model.Wind
import javax.inject.Inject


class ApiMapperImpl @Inject constructor(): ApiMapper {
    override fun mapApiCityListResponseToDomain(apiCityListResponse: ApiCityListResponse): CityListResponse {
        return with(apiCityListResponse) {
            CityListResponse(
                list = list?.map {
                    mapApiWeatherResultToDomain(it)
                }
            )
        }
    }

    private fun mapApiWeatherResultToDomain(result: ApiWeatherResult) = with(result) {
        WeatherResult(
            id =  id,
            name =  name,
            coord = Coord(
                lon =  coord?.lon,
                lat =  coord?.lat
            ),
            main = Main(
                temp =  main?.temp
            ),
            dt =  dt,
            wind = Wind(
                speed =  wind?.speed,
                deg =  wind?.deg
            ),
            sys = Sys(
                country =  sys?.country
            ),
            rain = Rain(
                h = null
            ),
            snow = null,
            clouds = Clouds(
                all =  clouds?.all
            ),
            weather =   weather?.map {
                mapApiWeatherToDomain(it)
            }

        )

    }
    override fun mapApiForecastToDomain(apiForecast: ApiForecast): Forecast {
        return with(apiForecast) {
            Forecast(
                list = apiForecast.list?.map {
                    mapApiForecastResultToDomain(it)
                },
                city = city?.let { mapApiCityToDomain(it) }
            )


        }
    }


    private fun mapApiCityToDomain(city: ApiCity): City{
        return with(city){
            City(
                id = id,
                name = name,
                coord = Coord(
                    lon = coord?.lon,
                    lat = coord?.lat
                ),
                country = country,
                timezone = timezone
            )
        }
    }

    private fun mapApiForecastResultToDomain(apiForecastResult: ApiForecastResult) = with(apiForecastResult){
        ForecastResult(
            dt = dt,
            main = Main(
                temp = main?.temp
            ),
            weather = weather?.map { mapApiWeatherToDomain(it) },
            clouds = Clouds(
                all = clouds?.all
            ),
            wind = Wind(
                speed = wind?.speed,
                deg = wind?.deg
            ),
            rain = Rain(
                h = rain?.h
            ),
            sys = ForecastSys(
                pod = sys?.pod
            ),
            dtTxt = dtTxt

        )
    }

    private fun mapApiWeatherToDomain(weather: ApiWeather) = with(weather){
        Weather(
            id = id,
            main = main,
            description = description,
            icon = icon
        )
    }


}

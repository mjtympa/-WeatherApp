package com.mujeeb.weatherapp.presentation.listener

import com.mujeeb.weatherapp.presentation.viewstate.CityViewState
import com.mujeeb.weatherapp.presentation.viewstate.WeatherResultViewState


interface CityListListener {

    fun onItemSelected(result : WeatherResultViewState)
}

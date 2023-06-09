package com.mujeeb.weatherapp.presentation.listener

import com.mujeeb.weatherapp.data.model.city_list.Result

interface CityListListener {

    fun onItemSelected(cityList: Result)
}

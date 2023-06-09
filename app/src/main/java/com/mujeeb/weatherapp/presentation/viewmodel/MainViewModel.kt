package com.mujeeb.weatherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mujeeb.weatherapp.domain.usecases.SearchCity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchCity: SearchCity,
) : ViewModel() {

    fun searchLocation(): MutableStateFlow<String> = searchCity.location

    fun getLocation(): LiveData<String> = searchCity.searchItem
}

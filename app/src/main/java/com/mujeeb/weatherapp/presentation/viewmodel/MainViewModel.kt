package com.mujeeb.weatherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mujeeb.weatherapp.domain.usecases.SearchCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchCityUseCase: SearchCityUseCase,
) : ViewModel() {

    fun searchLocation(): MutableStateFlow<String> = searchCityUseCase.location

    fun getLocation(): LiveData<String> = searchCityUseCase.searchItem
}

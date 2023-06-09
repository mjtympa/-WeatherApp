package com.mujeeb.weatherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mujeeb.weatherapp.common.utils.DataHandler
import com.mujeeb.weatherapp.common.utils.NetworkUtils
import com.mujeeb.weatherapp.data.enums.ErrorModel
import com.mujeeb.weatherapp.data.enums.ErrorType
import com.mujeeb.weatherapp.data.model.city_details.Response
import com.mujeeb.weatherapp.domain.usecases.cityDetails.AddCityDetails
import com.mujeeb.weatherapp.domain.usecases.cityDetails.GetLocalCityDetails
import com.mujeeb.weatherapp.domain.usecases.cityDetails.GetRemoteCityDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CityDetailsViewModel @Inject constructor(
    private val getRemoteCityDetails: GetRemoteCityDetails,
    private val getLocalCityDetails: GetLocalCityDetails,
    private val addCityDetails: AddCityDetails,
    private val networkUtils: NetworkUtils,
) : ViewModel() {

    private val _result = MutableLiveData<DataHandler<Response>>()
    val result: LiveData<DataHandler<Response>> = _result

    fun getWeatherData(city: Int) {
        viewModelScope.launch {
            try {
                _result.value = DataHandler.LOADING()
                if (networkUtils.isOnline()) {
                    val weatherResponse = getRemoteCityDetails(city)
                    _result.value = DataHandler.SUCCESS(weatherResponse)
                    addCityDetails(weatherResponse)
                } else {
                    _result.value = DataHandler.ERROR(error = ErrorModel(ErrorType.NETWORK))
                    val response = getLocalCityDetails()
                    _result.value = DataHandler.SUCCESS(response)
                }
            } catch (error: Throwable) {
                _result.value = DataHandler.ERROR(error = ErrorModel(ErrorType.UNKNOWN))
            }
        }
    }
}

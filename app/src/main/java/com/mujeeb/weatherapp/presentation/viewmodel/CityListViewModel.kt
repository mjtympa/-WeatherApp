package com.mujeeb.weatherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mujeeb.weatherapp.common.utils.DataHandler
import com.mujeeb.weatherapp.common.utils.NetworkUtils
import com.mujeeb.weatherapp.data.enums.ErrorModel
import com.mujeeb.weatherapp.data.enums.ErrorType
import com.mujeeb.weatherapp.data.model.city_list.CityResponse
import com.mujeeb.weatherapp.domain.usecases.cityList.AddCityList
import com.mujeeb.weatherapp.domain.usecases.cityList.GetLocalCityList
import com.mujeeb.weatherapp.domain.usecases.cityList.GetRemoteCityList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    private val getRemoteCityList: GetRemoteCityList,
    private val getLocalCityList: GetLocalCityList,
    private val addCityList: AddCityList,
    private val networkUtils: NetworkUtils,
) : ViewModel() {

    private val _cityResult = MutableLiveData<DataHandler<CityResponse>>()
    val cityResult: LiveData<DataHandler<CityResponse>> = _cityResult

    fun searchCity(city: String) {
        viewModelScope.launch {
            try {
                _cityResult.value = DataHandler.LOADING()
                if (networkUtils.isOnline()) {
                    val cityResponse = getRemoteCityList(city)
                    _cityResult.value = DataHandler.SUCCESS(cityResponse)
                    addCityList(cityResponse)
                } else {
                    _cityResult.value = DataHandler.ERROR(error = ErrorModel(ErrorType.NETWORK))
                    val response = getLocalCityList()
                    _cityResult.value = DataHandler.SUCCESS(response)
                }
            } catch (error: Throwable) {
                _cityResult.value = DataHandler.ERROR(error = ErrorModel(ErrorType.UNKNOWN))
            }
        }
    }
}

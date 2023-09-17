package com.mujeeb.weatherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mujeeb.weatherapp.utils.DataHandler
import com.mujeeb.weatherapp.utils.NetworkUtils
import com.mujeeb.weatherapp.data.enums.ErrorModel
import com.mujeeb.weatherapp.data.enums.ErrorType
import com.mujeeb.weatherapp.domain.usecases.AddForecastUseCase
import com.mujeeb.weatherapp.domain.usecases.GetLocalForecastUseCase
import com.mujeeb.weatherapp.domain.usecases.GetRemoteForecastUseCase
import com.mujeeb.weatherapp.presentation.mapper.ForecastViewStateMapper
import com.mujeeb.weatherapp.presentation.viewstate.ForecastViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CityForecastViewModel @Inject constructor(
    private val getRemoteForecastUseCase: GetRemoteForecastUseCase,
    private val getLocalForecastUseCase: GetLocalForecastUseCase,
    private val addForecastUseCase: AddForecastUseCase,
    private val mapper: ForecastViewStateMapper
) : ViewModel() {

    private val _result = MutableLiveData<DataHandler<ForecastViewState>>()
    val result: LiveData<DataHandler<ForecastViewState>> = _result

    fun getWeatherData(city: Int) {
        viewModelScope.launch {
            getRemoteForecastUseCase(city).data?.let {
                _result.value =  DataHandler.SUCCESS(mapper.mapDomainForecastToViewState(it))
                addForecastUseCase(it)
            }
        }
    }

    fun retrievedSavedLocalData(){
        viewModelScope.launch {
            getLocalForecastUseCase().data?.let {
                _result.value = DataHandler.SUCCESS(mapper.mapDomainForecastToViewState(it))
            }
        }
    }
}

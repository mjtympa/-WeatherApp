package com.mujeeb.weatherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mujeeb.weatherapp.utils.DataHandler
import com.mujeeb.weatherapp.domain.usecases.AddCityListUseCase
import com.mujeeb.weatherapp.domain.usecases.GetLocalCityListUseCase
import com.mujeeb.weatherapp.domain.usecases.GetRemoteCityListUseCase
import com.mujeeb.weatherapp.presentation.mapper.ForecastViewStateMapper
import com.mujeeb.weatherapp.presentation.viewstate.CityListResponseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    private val getRemoteCityListUseCase: GetRemoteCityListUseCase,
    private val getLocalCityListUseCase: GetLocalCityListUseCase,
    private val addCityListUseCase: AddCityListUseCase,
    private val mapper: ForecastViewStateMapper
) : ViewModel() {

    private val _result = MutableLiveData<DataHandler<CityListResponseViewState>>()
    val result: LiveData<DataHandler<CityListResponseViewState>> = _result

    fun searchCity(city: String) {
        viewModelScope.launch {
             getRemoteCityListUseCase(city).data?.let {
                 _result.value =  DataHandler.SUCCESS(mapper.mapDomainCityListResponseToViewState(it))
                 addCityListUseCase(it)
             }
        }
    }

    fun retrievedSavedLocalData(){
        viewModelScope.launch {
            getLocalCityListUseCase().data?.let {
                _result.value = DataHandler.SUCCESS(mapper.mapDomainCityListResponseToViewState(it))
            }
        }
    }
}

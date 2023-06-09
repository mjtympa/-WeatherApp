package com.mujeeb.weatherapp.domain.usecases

import androidx.lifecycle.asLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class SearchCity @Inject constructor() {

    val location = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val locationUIState: Flow<String> = location
        .debounce(SEARCH_DELAY_MILLIS)
        .mapLatest {
            if (it.length > MIN_QUERY_LENGTH) {
                it
            } else {
                ""
            }
        }
        .catch {
            // Log Error
        }

    val searchItem = locationUIState.asLiveData()

    companion object {
        private const val SEARCH_DELAY_MILLIS = 500L
        private const val MIN_QUERY_LENGTH = 2
    }
}

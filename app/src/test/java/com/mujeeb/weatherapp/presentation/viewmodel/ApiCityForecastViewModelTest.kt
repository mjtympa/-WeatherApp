package com.mujeeb.weatherapp.presentation.viewmodel

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mujeeb.weatherapp.utils.NetworkUtils
import com.mujeeb.weatherapp.domain.usecases.AddForecastUseCase
import com.mujeeb.weatherapp.domain.usecases.GetLocalForecastUseCase
import com.mujeeb.weatherapp.domain.usecases.GetRemoteForecastUseCase
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ApiCityForecastViewModelTest {
    private val addForecastUseCase = mockk<AddForecastUseCase>()
    private val getLocalForecastUseCase = mockk<GetLocalForecastUseCase>()
    private val getRemoteForecastUseCase = mockk<GetRemoteForecastUseCase>()
    private val networkUtils = mockk<NetworkUtils>()
    private val response = mockk<Response>()

    private lateinit var viewModel: CityForecastViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        viewModel = CityForecastViewModel(getRemoteForecastUseCase, getLocalForecastUseCase, addForecastUseCase, networkUtils)
    }

    @Test
    fun `GIVEN a device is online WHEN city weather data is requested THEN  the city weather data is returned`() = runTest {
        // GIVEN
        every { networkUtils.isOnline() } returns true
        coEvery { getRemoteForecastUseCase.invoke(CITY_ID) } returns response
        coEvery { addForecastUseCase.invoke(response) } returns Unit

        // WHEN
        viewModel.getWeatherData(CITY_ID)

        // THEN
        Assert.assertEquals(viewModel.result.value?.data, response)
    }

    @Test
    fun `GIVEN a device is online WHEN city weather data is requested THEN  the city weather data is cached`() = runTest {
        // GIVEN
        every { networkUtils.isOnline() } returns true
        coEvery { getRemoteForecastUseCase.invoke(CITY_ID) } returns response

        // WHEN
        viewModel.getWeatherData(CITY_ID)

        // THEN
        coVerify { addForecastUseCase.invoke(response) }
    }

    @Test
    fun `GIVEN a device is offline WHEN city weather data is requested THEN  the city weather data is returned (via cached data) `() = runTest {
        // GIVEN
        every { networkUtils.isOnline() } returns false
        coEvery { getLocalForecastUseCase.invoke() } returns response

        // WHEN
        viewModel.getWeatherData(CITY_ID)

        // THEN
        Assert.assertEquals(viewModel.result.value?.data, response)
    }

    companion object {
        private const val CITY_ID = 2355474 // id for London
    }
}

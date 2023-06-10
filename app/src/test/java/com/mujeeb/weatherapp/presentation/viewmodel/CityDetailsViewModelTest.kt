package com.mujeeb.weatherapp.presentation.viewmodel

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mujeeb.weatherapp.common.utils.NetworkUtils
import com.mujeeb.weatherapp.data.model.city_details.Response
import com.mujeeb.weatherapp.domain.usecases.cityDetails.AddCityDetails
import com.mujeeb.weatherapp.domain.usecases.cityDetails.GetLocalCityDetails
import com.mujeeb.weatherapp.domain.usecases.cityDetails.GetRemoteCityDetails
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CityDetailsViewModelTest {
    private val addCityDetails = mockk<AddCityDetails>()
    private val getLocalCityDetails = mockk<GetLocalCityDetails>()
    private val getRemoteCityDetails = mockk<GetRemoteCityDetails>()
    private val networkUtils = mockk<NetworkUtils>()
    private val response = mockk<Response>()

    private lateinit var viewModel: CityDetailsViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        viewModel = CityDetailsViewModel(getRemoteCityDetails, getLocalCityDetails, addCityDetails, networkUtils)
    }

    @Test
    fun `GIVEN a device is online WHEN city weather data is requested THEN  the city weather data is returned`() = runTest {
        // GIVEN
        every { networkUtils.isOnline() } returns true
        coEvery { getRemoteCityDetails.invoke(CITY_ID) } returns response
        coEvery { addCityDetails.invoke(response) } returns Unit

        // WHEN
        viewModel.getWeatherData(CITY_ID)

        // THEN
        Assert.assertEquals(viewModel.result.value?.data, response)
    }

    @Test
    fun `GIVEN a device is online WHEN city weather data is requested THEN  the city weather data is cached`() = runTest {
        // GIVEN
        every { networkUtils.isOnline() } returns true
        coEvery { getRemoteCityDetails.invoke(CITY_ID) } returns response

        // WHEN
        viewModel.getWeatherData(CITY_ID)

        // THEN
        coVerify { addCityDetails.invoke(response) }
    }

    @Test
    fun `GIVEN a device is offline WHEN city weather data is requested THEN  the city weather data is returned (via cached data) `() = runTest {
        // GIVEN
        every { networkUtils.isOnline() } returns false
        coEvery { getLocalCityDetails.invoke() } returns response

        // WHEN
        viewModel.getWeatherData(CITY_ID)

        // THEN
        Assert.assertEquals(viewModel.result.value?.data, response)
    }

    companion object {
        private const val CITY_ID = 2355474 // id for London
    }
}

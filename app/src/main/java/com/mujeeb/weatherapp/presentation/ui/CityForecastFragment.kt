package com.mujeeb.weatherapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mujeeb.weatherapp.R
import com.mujeeb.weatherapp.utils.CustomDialog
import com.mujeeb.weatherapp.utils.DataHandler
import com.mujeeb.weatherapp.data.enums.ErrorType
import com.mujeeb.weatherapp.databinding.FragmentWeatherListBinding
import com.mujeeb.weatherapp.presentation.adapter.CityForecastAdapter
import com.mujeeb.weatherapp.presentation.viewmodel.CityForecastViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CityForecastFragment : Fragment() {

    private val viewModel: CityForecastViewModel by viewModels()

    @Inject
    lateinit var cityForecastAdapter: CityForecastAdapter

    private lateinit var binding: FragmentWeatherListBinding

    private val args: CityForecastFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(this.context)
        with(binding.rvWeatherList) {
            layoutManager = linearLayoutManager
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    linearLayoutManager.orientation,
                ),
            )
            adapter = cityForecastAdapter
        }

        viewModel.getWeatherData(args.id)
        viewModel.result.observe(viewLifecycleOwner) { dataHandler ->
            when (dataHandler) {
                is DataHandler.LOADING -> binding.pbWeatherList.visibility = View.VISIBLE

                is DataHandler.SUCCESS -> dataHandler.data?.let { response ->
                    binding.pbWeatherList.visibility = View.INVISIBLE
                    response.list?.let {
                        cityForecastAdapter.updateList(it)
                    }
                }

                is DataHandler.ERROR -> dataHandler.error?.let {
                    if (it.errorType == ErrorType.NETWORK) {
                        showNoNetworkErrorMsg()
                        viewModel.retrievedSavedLocalData()
                    } else {
                        CustomDialog.showSomethingWentWrongDialog(requireActivity())
                    }
                }
            }
        }
    }

    private fun showNoNetworkErrorMsg() {
        Toast.makeText(requireContext(), "No network", Toast.LENGTH_SHORT)
    }
}

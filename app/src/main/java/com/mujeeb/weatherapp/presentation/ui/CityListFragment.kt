package com.mujeeb.weatherapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mujeeb.weatherapp.R
import com.mujeeb.weatherapp.common.utils.CustomDialog
import com.mujeeb.weatherapp.common.utils.DataHandler
import com.mujeeb.weatherapp.data.enums.ErrorType
import com.mujeeb.weatherapp.data.model.city_list.Result
import com.mujeeb.weatherapp.databinding.FragmentCityListBinding
import com.mujeeb.weatherapp.presentation.adapter.CityListAdapter
import com.mujeeb.weatherapp.presentation.listener.CityListListener
import com.mujeeb.weatherapp.presentation.viewmodel.CityListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CityListFragment : Fragment(), CityListListener {

    private val viewModel: CityListViewModel by viewModels()

    @Inject
    lateinit var cityListAdapter: CityListAdapter

    private lateinit var binding: FragmentCityListBinding

    private val args: CityListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_city_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(this.context)
        with(binding.rvCityList) {
            layoutManager = linearLayoutManager
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    linearLayoutManager.orientation,
                ),
            )
            adapter = cityListAdapter
        }

        viewModel.searchCity(args.city)
        viewModel.cityResult.observe(viewLifecycleOwner) { dataHandler ->
            when (dataHandler) {
                is DataHandler.SUCCESS -> dataHandler.data?.let { city ->
                    binding.pbCityList.visibility = View.INVISIBLE
                    city.list?.let {
                        cityListAdapter.updateList(it)
                    }
                }

                is DataHandler.LOADING -> binding.pbCityList.visibility = View.VISIBLE

                is DataHandler.ERROR -> dataHandler.error?.let {
                    if (it.errorType == ErrorType.NETWORK) {
                        showNoNetworkErrorMsg()
                    } else {
                        CustomDialog.showSomethingWentWrongDialog(requireActivity()) {}
                    }
                }
            }
        }
    }

    private fun showNoNetworkErrorMsg() {
        Toast.makeText(requireContext(), "No network", Toast.LENGTH_SHORT)
    }

    override fun onItemSelected(cityList: Result) {
        cityList.id?.let {
            findNavController().navigate(
                CityListFragmentDirections.navigateToDetailsFromList(
                    it,
                ),
            )
        }
    }
}

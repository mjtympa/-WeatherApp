package com.mujeeb.weatherapp.presentation.ui

import android.app.SearchManager
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.mujeeb.weatherapp.R
import com.mujeeb.weatherapp.databinding.ActivityMainBinding
import com.mujeeb.weatherapp.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var searchView: SearchView
    private var queryTextListener: SearchView.OnQueryTextListener? = null
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        val navController = host.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                destination.id.toString()
            }

            Timber.d("Navigated to $dest")
        }

        initObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.action_search)
        searchItem?.let {
            searchView = it.actionView as SearchView
            with(searchView) {
                setSearchableInfo(searchManager.getSearchableInfo(componentName))
                queryHint = resources.getString(R.string.search_hint)
                isQueryRefinementEnabled = true
                queryTextListener = object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(newText: String): Boolean {
                        mainViewModel.searchLocation().value = newText
                        return false
                    }
                    override fun onQueryTextSubmit(query: String): Boolean {
                        showSearchResult(query)
                        return true
                    }
                }
                setOnQueryTextListener(queryTextListener)
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    fun showSearchResult(city: String) {
        findNavController(R.id.nav_host_fragment)
            .setGraph(
                R.navigation.main_nav,
                CityListFragmentArgs(city).toBundle(),
            )
    }

    private fun initObservers() {
        mainViewModel.getLocation().observe(this) {
            if (it.isNotEmpty()) {
                showSearchResult(it)
            }
        }
    }
}

package com.mujeeb.weatherapp.di.module

import android.content.Context
import androidx.fragment.app.Fragment
import com.mujeeb.weatherapp.presentation.adapter.CityListAdapter
import com.mujeeb.weatherapp.presentation.listener.CityListListener
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(FragmentComponent::class)
@Module
object CityListAdapterModule {

    @Provides
    fun provideCityListRecyclerViewAdapter(cityListListener: CityListListener, @ApplicationContext context: Context, picasso: Picasso): CityListAdapter {
        return CityListAdapter(cityListListener, context, picasso)
    }

    @Provides
    fun provideSelectCityInterface(fragment: Fragment): CityListListener {
        return fragment as CityListListener
    }
}

package com.mujeeb.weatherapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mujeeb.weatherapp.R
import com.mujeeb.weatherapp.data.enums.Direction
import com.mujeeb.weatherapp.presentation.viewstate.ForecastResultViewState
import com.mujeeb.weatherapp.utils.toEstimatedDirection
import com.mujeeb.weatherapp.utils.toTimeStampFromDate
import com.squareup.picasso.Picasso
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CityForecastAdapter @Inject constructor(@ApplicationContext val context: Context, private val picasso: Picasso) :
    RecyclerView.Adapter<CityForecastAdapter.ViewHolder>() {

    private var mValues: List<ForecastResultViewState>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_weather_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCityData = mValues?.get(position)

        holder.temperature.text = String.format("%s %s", context.getString(R.string.current_temperature), currentCityData?.main?.temp)

        holder.windSpeed.text = String.format("%s %s", context.getString(R.string.wind_speed), currentCityData?.wind?.speed)

        holder.info.text = currentCityData?.weather?.get(0)?.description

        currentCityData?.dt?.let { holder.timeOfMeasurement.text = it.toTimeStampFromDate() }

        var direction: Direction? = null

        currentCityData?.wind?.deg?.let { it.toEstimatedDirection() }

        val imageUrl = getImageUrl(direction)

        val imageView = holder.windDirectionImage

        if (imageUrl != 0) {
            picasso.load(imageUrl)
                .into(imageView)
        }
    }

    override fun getItemCount(): Int {
        return mValues?.size ?: 0
    }

    fun updateList(forecast: List<ForecastResultViewState>?) {
        this.mValues = forecast
        notifyDataSetChanged()
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {

        var temperature: TextView = itemView.findViewById(R.id.tv_current_temp)

        var windSpeed: TextView = itemView.findViewById(R.id.tv_wind_speed)

        var info: TextView = itemView.findViewById(R.id.tv_info)

        var timeOfMeasurement: TextView = itemView.findViewById(R.id.tv_measurement_time)

        var windDirectionImage: ImageView = itemView.findViewById(R.id.iv_wind_direction)
    }

    private fun getImageUrl(direction: Direction?): Int {
        return when (direction) {
            Direction.North -> R.drawable.icons8_north_direction_48
            Direction.East -> R.drawable.icons8_east_direction_48
            Direction.South -> R.drawable.icons8_south_direction_48
            else -> R.drawable.icons8_west_direction_48
        }
    }
}

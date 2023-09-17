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
import com.mujeeb.weatherapp.presentation.listener.CityListListener
import com.mujeeb.weatherapp.presentation.viewstate.WeatherResultViewState
import com.mujeeb.weatherapp.utils.toEstimatedDirection
import com.mujeeb.weatherapp.utils.toTimeStampFromDate
import com.squareup.picasso.Picasso
import dagger.hilt.android.qualifiers.ApplicationContext

class CityListAdapter(private val listener: CityListListener, @ApplicationContext val context: Context, private val picasso: Picasso) :
    RecyclerView.Adapter<CityListAdapter.ViewHolder>() {

    private var mValues: List<WeatherResultViewState>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_city_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = mValues?.get(position)

        holder.location.text = String.format("%s %s", result?.name, result?.sys?.country)

        holder.itemView.setOnClickListener {
            if (result != null) {
                listener.onItemSelected(result)
            }
        }

        holder.temperature.text = String.format("%s %s", context.getString(R.string.current_temperature), result?.main?.temp)

        holder.windSpeed.text = String.format("%s %s", context.getString(R.string.wind_speed), result?.wind?.speed)

        holder.info.text = result?.weather?.get(0)?.description

        result?.dt?.let { holder.timeOfMeasurement.text = it.toTimeStampFromDate() }

        var direction: Direction? = null

        result?.wind?.deg?.let { it.toEstimatedDirection() }

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

    fun updateList(cities: List<WeatherResultViewState>) {
        this.mValues = cities
        notifyDataSetChanged()
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {

        var temperature: TextView = itemView.findViewById(R.id.tv_weather_temperature)

        var windSpeed: TextView = itemView.findViewById(R.id.tv_speed_wind)

        var info: TextView = itemView.findViewById(R.id.tv_weather_info)

        var timeOfMeasurement: TextView = itemView.findViewById(R.id.tv_weather_time)

        var windDirectionImage: ImageView = itemView.findViewById(R.id.iv_direction_wind)

        var location: TextView = itemView.findViewById(R.id.tv_location_name)
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

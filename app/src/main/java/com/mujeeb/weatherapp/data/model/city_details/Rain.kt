package com.mujeeb.weatherapp.data.model.city_details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rain {

    @SerializedName("3h")
    @Expose
    var h: Double? = null
}

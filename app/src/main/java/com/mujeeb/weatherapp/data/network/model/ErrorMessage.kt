package com.mujeeb.weatherapp.data.network.model

import com.google.gson.annotations.SerializedName

data class ErrorMessage(
    @SerializedName("message") val message: String
)

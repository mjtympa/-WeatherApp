package com.mujeeb.weatherapp.data.network.model

import com.google.gson.annotations.SerializedName
import com.mujeeb.weatherapp.data.network.model.ErrorMessage

data class ErrorResponse(
    @SerializedName("error") val error: ErrorMessage
)

package com.example.lazycolumn.models

import com.google.gson.annotations.SerializedName

data class Weather (
    val location: Location?,
    val current: Current?,
    val forecast: Forecast?
)

data class Location(
    @SerializedName("name")
    val name: String
)

data class Current(
    @SerializedName("temp_c")
    val temp: String
)

data class Forecast(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val date: String
)
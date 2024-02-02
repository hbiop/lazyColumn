package com.example.lazycolumn.retrofit

import com.example.lazycolumn.models.Weather
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {
    @GET("v1/forecast.json?key=ccdf90f3051847bc820143629231410&q=London&days=3&aqi=no&alerts=no")
    fun getWeather(): Call<Weather>
}
package com.example.lazycolumn

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lazycolumn.models.ForecastDay
import com.example.lazycolumn.models.Weather
import com.example.lazycolumn.retrofit.RetrofitApi
import com.example.lazycolumn.ui.theme.LazyColumnTheme
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumnTheme {
                // A surface container using the 'background' color from the theme
                getData()

            }
        }
    }
}

@Composable
fun Greeting(list: List<ForecastDay>) {
    LazyColumn()
    {
        itemsIndexed(items = list) { _, item -> Text(text = item, fontSize = 20.dp) }
    }
}


fun getData() {

    val model = Retrofit.Builder().baseUrl("http://api.weatherapi.com")
        .addConverterFactory(GsonConverterFactory.create()).build()
    val weatherApi: RetrofitApi = model.create(RetrofitApi::class.java)
    val call: Call<Weather> = weatherApi.getWeather()
    call.enqueue(object : Callback<Weather> {
        override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
            Log.d("a", "Success")
            Log.d("a", response.body().toString())
            val weather = response.body()
            Log.d("a", weather?.current?.temp.toString())
            Log.d("a", weather?.forecast?.forecastday?.get(0)?.date.toString())
            val list = weather?.forecast?.forecastday
        }

        override fun onFailure(call: Call<Weather>, t: Throwable) {
            Log.d("a", "Failure")
        }
    })
}
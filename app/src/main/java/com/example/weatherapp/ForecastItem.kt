package com.example.weatherapp

data class ForecastItem(
    val dt_txt: String,           // Date and time of the forecast
    val main: Main,               // Main weather data (similar to current weather)
    val weather: List<Weather>    // List of weather descriptions
)

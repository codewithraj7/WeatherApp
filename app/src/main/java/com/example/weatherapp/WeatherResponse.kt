package com.example.weatherapp

data class WeatherResponse(
    val weather: List<Weather>,   // List of weather descriptions
    val main: Main,               // Main weather details (temperature, humidity, etc.)
    val name: String              // Name of the location (city)
)
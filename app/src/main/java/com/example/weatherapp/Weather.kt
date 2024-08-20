package com.example.weatherapp

data class Weather(
    val id: Int,                  // Weather condition id
    val main: String,             // Group of weather parameters (e.g., Rain, Snow)
    val description: String,      // Weather condition within the group (e.g., light rain)
    val icon: String              // Weather icon id
)

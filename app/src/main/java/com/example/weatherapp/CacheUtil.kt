package com.example.weatherapp

import android.content.Context
import com.google.gson.Gson

object CacheUtil {
    private const val WEATHER_PREFS = "WeatherPrefs"
    private const val CURRENT_WEATHER_KEY = "CurrentWeather"
    private const val FORECAST_KEY = "Forecast"

    fun cacheWeatherData(context: Context, weather: WeatherResponse) {
        val json = Gson().toJson(weather)
        context.getSharedPreferences(WEATHER_PREFS, Context.MODE_PRIVATE)
            .edit()
            .putString(CURRENT_WEATHER_KEY, json)
            .apply()
    }

    fun getCachedWeatherData(context: Context): WeatherResponse? {
        val json = context.getSharedPreferences(WEATHER_PREFS, Context.MODE_PRIVATE)
            .getString(CURRENT_WEATHER_KEY, null)
        return json?.let { Gson().fromJson(it, WeatherResponse::class.java) }
    }

    fun cacheForecastData(context: Context, forecast: ForecastResponse) {
        val json = Gson().toJson(forecast)
        context.getSharedPreferences(WEATHER_PREFS, Context.MODE_PRIVATE)
            .edit()
            .putString(FORECAST_KEY, json)
            .apply()
    }

    fun getCachedForecastData(context: Context): ForecastResponse? {
        val json = context.getSharedPreferences(WEATHER_PREFS, Context.MODE_PRIVATE)
            .getString(FORECAST_KEY, null)
        return json?.let { Gson().fromJson(it, ForecastResponse::class.java) }
    }
}

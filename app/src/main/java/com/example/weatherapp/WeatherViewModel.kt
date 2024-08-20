package com.example.weatherapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val _weather = MutableLiveData<WeatherResponse?>()
    val weather: LiveData<WeatherResponse?> get() = _weather

    private val _forecast = MutableLiveData<ForecastResponse?>()
    val forecast: LiveData<ForecastResponse?> get() = _forecast

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    private val apiKey = "2d2f7cb871f3e39cb4be8a556f1d88a1"
    val appContext: Context = getApplication<Application>().applicationContext

    fun getCurrentWeather(location: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getCurrentWeather(location, apiKey)
                _weather.postValue(response)
                _error.postValue(null)
                CacheUtil.cacheWeatherData(appContext, response)
            } catch (e: Exception) {
                _weather.postValue(CacheUtil.getCachedWeatherData(appContext))
                _error.postValue("Failed to fetch weather data. Please check your connection.")
            }
        }
    }

    fun getWeatherForecast(location: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getWeatherForecast(location, apiKey)
                _forecast.postValue(response)
                _error.postValue(null)
                CacheUtil.cacheForecastData(appContext, response)
            } catch (e: Exception) {
                _forecast.postValue(CacheUtil.getCachedForecastData(appContext))
                _error.postValue("Failed to fetch forecast data. Please check your connection.")
            }
        }
    }
}

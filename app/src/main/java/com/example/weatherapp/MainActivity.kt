package com.example.weatherapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val locationEditText = findViewById<EditText>(R.id.locationEditText)
        val searchButton = findViewById<Button>(R.id.searchButton)

        val tempTextView = findViewById<TextView>(R.id.tempTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val locationTextView = findViewById<TextView>(R.id.locationTextView)
        val forecastTextView = findViewById<TextView>(R.id.forecastTextView)

        searchButton.setOnClickListener {
            val location = locationEditText.text.toString()
            if (location.isNotEmpty()) {
                viewModel.getCurrentWeather(location)
                viewModel.getWeatherForecast(location)
            } else {
                Toast.makeText(this, "Please enter a location", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.weather.observe(this, Observer { weather ->
            weather?.let {
                tempTextView.text = "${it.main.temp}°C"
                descriptionTextView.text = it.weather[0].description
                locationTextView.text = it.name
            }
        })

        viewModel.forecast.observe(this, Observer { forecast ->
            forecast?.let {
                val forecastText = it.list.joinToString("\n") { item ->
                    "${item.dt_txt}: ${item.main.temp}°C - ${item.weather[0].description}"
                }
                forecastTextView.text = forecastText
            }
        })

        viewModel.error.observe(this, Observer { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }
}

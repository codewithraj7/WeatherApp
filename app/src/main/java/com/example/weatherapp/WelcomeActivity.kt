package com.example.weatherapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Check if the app is being opened for the first time
        val sharedPreferences: SharedPreferences = getSharedPreferences("WeatherAppPrefs", MODE_PRIVATE)
        val isFirstTime = sharedPreferences.getBoolean("isFirstTime", true)

        if (isFirstTime) {
            // Set isFirstTime to false so this screen is not shown again
            val editor = sharedPreferences.edit()
            editor.putBoolean("isFirstTime", false)
            editor.apply()

            // Display the welcome screen for 3 seconds
            Handler(Looper.getMainLooper()).postDelayed({
                // Start the MainActivity after the delay
                startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
                finish() // Close the WelcomeActivity
            }, 3000) // 3000 milliseconds = 3 seconds
        } else {
            // If not the first time, directly go to the main activity
            startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
            finish() // Close the WelcomeActivity
        }
    }
}

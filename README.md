
# WeatherApp
<img align="right" src="previews/preview_1.gif" width="33%"/>
Android Weather app developed in Kotlin. 
Displays current weather conditions with temperature, describtion and forecast information.
It also allows to search the weather by a city name. 


The app uses the OpenWeatherMap website for the weather Information.
Using the URL: https://api.openweathermap.org/data/2.5/

Created a WeatherApiService inferface using retrofit and alos singleton RetrofitInstance class to manage API calls.
and also the data classes like WeatherResponse, Weather, Main, ForecastResponse, ForecastItem

Created the WeatherViewModel class to handle the logic for fetching data from the API.
And aslo Implemented the cache and retry mechanism.

## Preview
![WeatherAppDemo](https://github.com/user-attachments/assets/6ca2af85-e80f-4907-91cf-12c2647ccaf6)


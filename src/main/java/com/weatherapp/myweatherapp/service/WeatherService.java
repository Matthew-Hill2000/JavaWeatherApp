package com.weatherapp.myweatherapp.service;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// This marks this class as a Spring service component
@Service
public class WeatherService {

  // This injects an instance of VisualcrossingRepository into this service
  @Autowired
  VisualcrossingRepository weatherRepo;

  // This retrieves the weather forecast for a given city
  public CityInfo forecastByCity(String city) {
    // Call the repository to get the forecast data for the specified city
    return weatherRepo.getByCity(city);
  }

  // This compares the daylight hours between two cities
  public String compareDaylight(String city1, String city2) {
    // Retrieve weather data for both cities
    CityInfo city1Info = weatherRepo.getByCity(city1);
    CityInfo city2Info = weatherRepo.getByCity(city2);

    // Define a formatter to parse the sunrise and sunset times
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    // Parse the sunrise and sunset times for both cities
    LocalTime city1Sunrise = LocalTime.parse(city1Info.getCurrentConditions().getSunrise(), timeFormatter);
    LocalTime city1Sunset = LocalTime.parse(city1Info.getCurrentConditions().getSunset(), timeFormatter);
    LocalTime city2Sunrise = LocalTime.parse(city2Info.getCurrentConditions().getSunrise(), timeFormatter);
    LocalTime city2Sunset = LocalTime.parse(city2Info.getCurrentConditions().getSunset(), timeFormatter);

    // Calculate the total daylight duration for both cities in seconds
    int city1Daylight = city1Sunset.toSecondOfDay() - city1Sunrise.toSecondOfDay();
    int city2Daylight = city2Sunset.toSecondOfDay() - city2Sunrise.toSecondOfDay();

    // Compare the daylight durations and return the result
    if (city1Daylight > city2Daylight) {
      return city1 + " has the longest day";
    } else if (city2Daylight > city1Daylight) {
      return city2 + " has the longest day";
    } else {
      return "Both cities have the same length of day";
    }
  }

  // This checks if it is raining in either of the two cities
  public String checkRainInCities(String city1, String city2) {
    // Retrieve weather data for both cities
    CityInfo city1Info = weatherRepo.getByCity(city1);
    CityInfo city2Info = weatherRepo.getByCity(city2);

    // Check if it is raining in either city
    boolean isRainingCity1 = city1Info.getCurrentConditions().getConditions().toLowerCase().contains("rain");
    boolean isRainingCity2 = city2Info.getCurrentConditions().getConditions().toLowerCase().contains("rain");

    // Return the result based on the rain conditions
    if (isRainingCity1 && isRainingCity2) {
      return "It is raining in both " + city1 + " and " + city2;
    } else if (isRainingCity1) {
      return "It is raining in " + city1;
    } else if (isRainingCity2) {
      return "It is raining in " + city2;
    } else {
      return "It is not raining in either city";
    }
  }

}

package com.weatherapp.myweatherapp.controller;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;

// This allows requests from the specified origin (localhost:3000) to access this controller
@CrossOrigin(origins = "http://localhost:3000")
// This marks this class as a Spring MVC controller that can handle HTTP
// requests
@Controller
public class WeatherController {

  // This injects an instance of WeatherService into this controller
  @Autowired
  WeatherService weatherService;

  // This handles GET requests to /forecast/{city} and returns the weather
  // forecast for the specified city
  @GetMapping("/forecast/{city}")
  public ResponseEntity<CityInfo> forecastByCity(@PathVariable("city") String city) {
    // Call the weatherService to get the forecast for the specified city
    CityInfo ci = weatherService.forecastByCity(city);
    // Return the forecast information wrapped in a ResponseEntity
    return ResponseEntity.ok(ci);
  }

  // This handles GET requests to /forecast/compare/daylight/{city1}/{city2} and
  // compares daylight hours between two cities
  @GetMapping("/forecast/compare/daylight/{city1}/{city2}")
  public ResponseEntity<String> compareDaylight(
      @PathVariable("city1") String city1,
      @PathVariable("city2") String city2) {
    // Call the weatherService to compare daylight hours between the two cities
    String result = weatherService.compareDaylight(city1, city2);
    // Return the comparison result wrapped in a ResponseEntity
    return ResponseEntity.ok(result);
  }

  // This handles GET requests to /forecast/compare/rain/{city1}/{city2} and
  // checks rain conditions in two cities
  @GetMapping("/forecast/compare/rain/{city1}/{city2}")
  public ResponseEntity<String> compareRain(
      @PathVariable("city1") String city1,
      @PathVariable("city2") String city2) {
    // Call the weatherService to check rain conditions in the two cities
    String result = weatherService.checkRainInCities(city1, city2);
    // Return the comparison result wrapped in a ResponseEntity
    return ResponseEntity.ok(result);
  }

}

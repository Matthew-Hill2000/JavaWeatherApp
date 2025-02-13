package com.weatherapp.myweatherapp.repository;

import com.weatherapp.myweatherapp.model.CityInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

// This marks this class as a Spring repository component
@Repository
public class VisualcrossingRepository {

  // Inject the URL and API key from the application properties
  @Value("${weather.visualcrossing.url}")
  String url;
  @Value("${weather.visualcrossing.key}")
  String key;

  // This retrieves weather data for a given city using the Visualcrossing API
  public CityInfo getByCity(String city) {
    // Construct the API endpoint URL with the city name and API key
    String uri = url + "timeline/" + city + "?key=" + key;
    // Create a RestTemplate instance to make the HTTP request
    RestTemplate restTemplate = new RestTemplate();
    // Make the GET request and return the response as a CityInfo object
    return restTemplate.getForObject(uri, CityInfo.class);
  }
}

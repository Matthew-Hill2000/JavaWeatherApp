package com.weatherapp.myweatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

// This class represents the weather information for a city
public class CityInfo {

  // These annotations map the JSON properties to the class fields
  @JsonProperty("address")
  String address;

  @JsonProperty("description")
  String description;

  @JsonProperty("currentConditions")
  CurrentConditions currentConditions;

  @JsonProperty("days")
  List<Days> days;

  // Getter and setter for currentConditions
  public CurrentConditions getCurrentConditions() {
    return currentConditions;
  }

  public void setCurrentConditions(CurrentConditions currentConditions) {
    this.currentConditions = currentConditions;
  }

  // This nested class represents the current weather conditions
  public static class CurrentConditions {
    @JsonProperty("temp")
    String currentTemperature;

    @JsonProperty("sunrise")
    String sunrise;

    @JsonProperty("sunset")
    String sunset;

    @JsonProperty("feelslike")
    String feelslike;

    @JsonProperty("humidity")
    String humidity;

    @JsonProperty("conditions")
    String conditions;

    // Getters and setters for the current conditions fields
    public String getConditions() {
      return conditions;
    }

    public String getSunrise() {
      return sunrise;
    }

    public String getSunset() {
      return sunset;
    }

    public void setConditions(String conditions) {
      this.conditions = conditions;
    }

    public void setSunrise(String sunrise) {
      this.sunrise = sunrise;
    }

    public void setSunset(String sunset) {
      this.sunset = sunset;
    }
  }

  // This nested class represents the weather conditions for a specific day
  public static class Days {

    @JsonProperty("datetime")
    String date;

    @JsonProperty("temp")
    String currentTemperature;

    @JsonProperty("tempmax")
    String maxTemperature;

    @JsonProperty("tempmin")
    String minTemperature;

    @JsonProperty("conditions")
    String conditions;

    @JsonProperty("description")
    String description;

  }

}

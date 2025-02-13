package com.weatherapp.myweatherapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

// This class contains unit tests for the WeatherService class
class WeatherServiceTest {

  // Mock the VisualcrossingRepository to simulate its behavior
  @Mock
  private VisualcrossingRepository weatherRepo;

  // Inject the mocked repository into the WeatherService instance
  @InjectMocks
  private WeatherService weatherService;

  // Initialize the mocks before each test
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    System.out.println("\n=== Starting new test ===");
  }

  // Test the scenario where it is raining in both cities
  @Test
  void checkRainInCities_BothCitiesRaining() {
    System.out.println("Testing scenario: Both cities raining");
    // Create mock data for London and Paris with rain conditions
    CityInfo london = createCityInfo("rain");
    CityInfo paris = createCityInfo("rain");

    System.out.println("London conditions: " + london.getCurrentConditions().getConditions());
    System.out.println("Paris conditions: " + paris.getCurrentConditions().getConditions());

    // Mock the repository responses
    when(weatherRepo.getByCity("London")).thenReturn(london);
    when(weatherRepo.getByCity("Paris")).thenReturn(paris);

    // Call the method under test
    String result = weatherService.checkRainInCities("London", "Paris");
    System.out.println("Result: " + result);

    // Verify the result
    assertEquals("It is raining in both London and Paris", result);
    System.out.println("Test passed");
  }

  // Test the scenario where it is raining in the first city only
  @Test
  void checkRainInCities_FirstCityRaining() {
    System.out.println("Testing scenario: First city raining");
    // Create mock data for London with rain and Paris with clear conditions
    CityInfo london = createCityInfo("rain");
    CityInfo paris = createCityInfo("clear");

    System.out.println("London conditions: " + london.getCurrentConditions().getConditions());
    System.out.println("Paris conditions: " + paris.getCurrentConditions().getConditions());

    // Mock the repository responses
    when(weatherRepo.getByCity("London")).thenReturn(london);
    when(weatherRepo.getByCity("Paris")).thenReturn(paris);

    // Call the method under test
    String result = weatherService.checkRainInCities("London", "Paris");
    System.out.println("Result: " + result);

    // Verify the result
    assertEquals("It is raining in London", result);
    System.out.println("Test passed");
  }

  // Test the scenario where it is raining in the second city only
  @Test
  void checkRainInCities_SecondCityRaining() {
    System.out.println("Testing scenario: Second city raining");
    // Create mock data for London with clear and Paris with rain conditions
    CityInfo london = createCityInfo("clear");
    CityInfo paris = createCityInfo("rain");

    System.out.println("London conditions: " + london.getCurrentConditions().getConditions());
    System.out.println("Paris conditions: " + paris.getCurrentConditions().getConditions());

    // Mock the repository responses
    when(weatherRepo.getByCity("London")).thenReturn(london);
    when(weatherRepo.getByCity("Paris")).thenReturn(paris);

    // Call the method under test
    String result = weatherService.checkRainInCities("London", "Paris");
    System.out.println("Result: " + result);

    // Verify the result
    assertEquals("It is raining in Paris", result);
    System.out.println("Test passed");
  }

  // Test the scenario where it is not raining in either city
  @Test
  void checkRainInCities_NoCitiesRaining() {
    System.out.println("Testing scenario: No cities raining");
    // Create mock data for London and Paris with clear conditions
    CityInfo london = createCityInfo("clear");
    CityInfo paris = createCityInfo("sunny");

    System.out.println("London conditions: " + london.getCurrentConditions().getConditions());
    System.out.println("Paris conditions: " + paris.getCurrentConditions().getConditions());

    // Mock the repository responses
    when(weatherRepo.getByCity("London")).thenReturn(london);
    when(weatherRepo.getByCity("Paris")).thenReturn(paris);

    // Call the method under test
    String result = weatherService.checkRainInCities("London", "Paris");
    System.out.println("Result: " + result);

    // Verify the result
    assertEquals("It is not raining in either city", result);
    System.out.println("Test passed");
  }

  // Test the scenario where the first city has longer daylight hours
  @Test
  void compareDaylight_FirstCityLonger() {
    System.out.println("Testing scenario: First city has longer daylight");

    // Create mock data for two cities with different daylight durations
    CityInfo city1 = createCityInfoWithDaylight("06:00:00", "20:00:00"); // 14 hours
    CityInfo city2 = createCityInfoWithDaylight("07:00:00", "19:00:00"); // 12 hours

    System.out.println("City1 daylight hours: " + city1.getCurrentConditions().getSunrise()
        + " to " + city1.getCurrentConditions().getSunset());
    System.out.println("City2 daylight hours: " + city2.getCurrentConditions().getSunrise()
        + " to " + city2.getCurrentConditions().getSunset());

    // Mock the repository responses
    when(weatherRepo.getByCity("City1")).thenReturn(city1);
    when(weatherRepo.getByCity("City2")).thenReturn(city2);

    // Call the method under test
    String result = weatherService.compareDaylight("City1", "City2");
    System.out.println("Result: " + result);

    // Verify the result
    assertEquals("City1 has the longest day", result);
    System.out.println("Test passed");
  }

  // Test the scenario where the second city has longer daylight hours
  @Test
  void compareDaylight_SecondCityLonger() {
    System.out.println("Testing scenario: Second city has longer daylight");
    // Create mock data for two cities with different daylight durations
    CityInfo city2 = createCityInfoWithDaylight("06:00:00", "20:00:00"); // 14 hours
    CityInfo city1 = createCityInfoWithDaylight("07:00:00", "19:00:00"); // 12 hours

    System.out.println("City1 daylight hours: " + city1.getCurrentConditions().getSunrise()
        + " to " + city1.getCurrentConditions().getSunset());
    System.out.println("City2 daylight hours: " + city2.getCurrentConditions().getSunrise()
        + " to " + city2.getCurrentConditions().getSunset());

    // Mock the repository responses
    when(weatherRepo.getByCity("City1")).thenReturn(city1);
    when(weatherRepo.getByCity("City2")).thenReturn(city2);

    // Call the method under test
    String result = weatherService.compareDaylight("City1", "City2");
    System.out.println("Result: " + result);

    // Verify the result
    assertEquals("City2 has the longest day", result);
    System.out.println("Test passed");
  }

  // Test the scenario where both cities have equal daylight hours
  @Test
  void compareDaylight_EqualLength() {
    System.out.println("Testing scenario: Neither city has longer daylight");
    // Create mock data for two cities with equal daylight durations
    CityInfo city1 = createCityInfoWithDaylight("06:00:00", "20:00:00"); // 14 hours
    CityInfo city2 = createCityInfoWithDaylight("06:00:00", "20:00:00"); // 14 hours

    System.out.println("City1 daylight hours: " + city1.getCurrentConditions().getSunrise()
        + " to " + city1.getCurrentConditions().getSunset());
    System.out.println("City2 daylight hours: " + city2.getCurrentConditions().getSunrise()
        + " to " + city2.getCurrentConditions().getSunset());

    // Mock the repository responses
    when(weatherRepo.getByCity("City1")).thenReturn(city1);
    when(weatherRepo.getByCity("City2")).thenReturn(city2);

    // Call the method under test
    String result = weatherService.compareDaylight("City1", "City2");
    System.out.println("Result: " + result);

    // Verify the result
    assertEquals("Both cities have the same length of day", result);
    System.out.println("Test passed");
  }

  // Helper method to create a CityInfo object with specified weather conditions
  private CityInfo createCityInfo(String conditions) {
    CityInfo cityInfo = new CityInfo();
    CityInfo.CurrentConditions currentConditions = new CityInfo.CurrentConditions();
    currentConditions.setConditions(conditions);
    cityInfo.setCurrentConditions(currentConditions);
    return cityInfo;
  }

  // Helper method to create a CityInfo object with specified sunrise and sunset
  // times
  private CityInfo createCityInfoWithDaylight(String sunrise, String sunset) {
    CityInfo cityInfo = new CityInfo();
    CityInfo.CurrentConditions currentConditions = new CityInfo.CurrentConditions();
    currentConditions.setSunrise(sunrise);
    currentConditions.setSunset(sunset);
    cityInfo.setCurrentConditions(currentConditions);
    return cityInfo;
  }
}
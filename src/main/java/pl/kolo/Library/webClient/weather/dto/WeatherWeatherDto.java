package pl.kolo.Library.webClient.weather.dto;

import lombok.Getter;

@Getter
public class WeatherWeatherDto {
    private WeatherMainDto main;
    private WeatherWindDto wind;
}

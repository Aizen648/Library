package pl.kolo.Library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kolo.Library.model.Dto.WeatherDto;
import pl.kolo.Library.webClient.weather.WeatherClient;

@RequiredArgsConstructor
@Service
@Slf4j
public class WeatherService {

    private final WeatherClient weatherClient;

    public WeatherDto getWeather(String city) {
        return weatherClient.getWeatherForCity(city);
    }
    public String isRaining(){
        return weatherClient.isRaining() ? "Daj reklamówkę by nie zmokły rzeczy" : "Piękna pogoda";
    }

}

package pl.kolo.Library.webClient.weather;

import org.junit.jupiter.api.Test;
import pl.kolo.Library.exception.exception.WeatherClientWrongCityException;
import pl.kolo.Library.model.Dto.WeatherDto;

import static org.junit.jupiter.api.Assertions.*;

class WeatherClientTest {

    WeatherClient weatherClient=new WeatherClient();
    @Test
    void WeatherClient_GetWeatherForCity() {
        WeatherDto weatherDto = weatherClient.getWeatherForCity("warszawa");

        assertNotNull(weatherDto);
    }
    @Test
    void WeatherClient_WrongNameCity() {
        assertThrows(WeatherClientWrongCityException.class,()->weatherClient.getWeatherForCity("warszawaa"));
    }
    @Test
    void WeatherClient_WrongNameCity2() {
        assertThrows(WeatherClientWrongCityException.class,()->weatherClient.getWeatherForCity("aaa"));
    }
}
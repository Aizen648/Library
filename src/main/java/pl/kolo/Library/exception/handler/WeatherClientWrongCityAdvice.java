package pl.kolo.Library.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.kolo.Library.exception.exception.WeatherClientWrongCityException;

@ControllerAdvice
public class WeatherClientWrongCityAdvice {
    @ExceptionHandler(WeatherClientWrongCityException.class)
    public ResponseEntity<String> handlerWeatherClientWrongCity(WeatherClientWrongCityException weatherClientWrongCityException){
        return ResponseEntity
                .status(weatherClientWrongCityException.getStatusCode())
                .body("Something is wrong with name: "+weatherClientWrongCityException.getMessage());
    }
}

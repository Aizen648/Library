package pl.kolo.Library.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class WeatherClientWrongCityException extends HttpClientErrorException  {

    public WeatherClientWrongCityException(HttpStatus statusCode, String statusText) {
        super(statusCode, "You entered the wrong city "+statusText);
    }
}

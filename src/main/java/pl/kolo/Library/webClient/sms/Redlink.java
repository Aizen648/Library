package pl.kolo.Library.webClient.sms;

import org.json.JSONObject;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import pl.kolo.Library.exception.exception.WeatherClientWrongCityException;
import pl.kolo.Library.model.Dto.WeatherDto;
import pl.kolo.Library.webClient.weather.dto.WeatherWeatherDto;

public class Redlink {
    private static final String WEATER_URL = "https://api.redlink.pl";
    private static final String API_KEY = "kolo";
    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherDto getWeatherForCity(String city) {
        WeatherWeatherDto openWeatherWeatherDto = callGetMethod("weather?q={city}&appid={apiKey}&units=metric&lang=pl",
                WeatherWeatherDto.class,
                city, API_KEY);
        return WeatherDto.builder()
                .temperature(openWeatherWeatherDto.getMain().getTemp())
                .pressure(openWeatherWeatherDto.getMain().getPressure())
                .humidity(openWeatherWeatherDto.getMain().getHumidity())
                .windSpeed(openWeatherWeatherDto.getWind().getSpeed())
                .build();
    }

//
//    public String getForecast(double lat, double lon) {
//        return callGetMethod("onecall?lat={lat}&lon={lon}&exclude=minutely,hourly&appid={apiKey}&units=metric&lang=pl",
//                String.class,
//                lat, lon, API_KEY);
//    }

    public boolean isRaining(){
        String jsonObject = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q=warszawa&appid=f65b1086a7b6097c839772eb388a612a&units=metric&lang=pl", String.class);
        JSONObject json= new JSONObject(jsonObject);
        JSONObject weather=json.getJSONArray("weather").getJSONObject(0);
        String main=weather.getString("main");
        return main.equals("Rain");
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        try {
            return restTemplate.getForObject(WEATER_URL + url,
                    responseType, objects);
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            if( ex.getResponseBodyAsString().contains("city not found"))
                throw new WeatherClientWrongCityException(ex.getStatusCode(),objects[0].toString());
        } catch (ResourceAccessException ex) {
            System.out.println("Wystąpił błąd: " + ex.getMessage());
        }
        throw new RuntimeException();
    }

}

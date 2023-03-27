package pl.kolo.Library.model.Dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WeatherDto {
    private float temperature;
    private int pressure;
    private int humidity;
    private float windSpeed;
}

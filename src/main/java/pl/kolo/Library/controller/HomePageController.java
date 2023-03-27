package pl.kolo.Library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.kolo.Library.exception.exception.MockException;
import pl.kolo.Library.model.Dto.WeatherDto;
import pl.kolo.Library.service.CatFactService;
import pl.kolo.Library.service.WeatherService;
import pl.kolo.Library.webClient.catFact.dto.CatFactDto;

@RestController
@RequiredArgsConstructor
public class HomePageController {

    private final CatFactService catFactService;
    private final WeatherService weatherService;

    @GetMapping
    public String sayHello(){
        return "Hello Kolo :D Project Start";
    }

    @GetMapping("/exception")
    public String myException(){
        throw new MockException(4);
    }

    @GetMapping("/randomFact/{count}")
    public CatFactDto randomFact(@PathVariable int count){
        return catFactService.getRandomCatFact(count);
    }

    @GetMapping("/randomFactAsString/{count}")
    public String randomFactAsString(@PathVariable int count){
        return catFactService.getRandomCatFactAsString(count);
    }

    @GetMapping("/weather/{city}")
    public WeatherDto getWeather(@PathVariable String city){
        return weatherService.getWeather(city);
    }

    @GetMapping("/isRaining")
    public String isRaining(){
        return weatherService.isRaining();
    }
}

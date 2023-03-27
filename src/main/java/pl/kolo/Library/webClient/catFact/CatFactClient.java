package pl.kolo.Library.webClient.catFact;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.kolo.Library.exception.exception.CatFactWrongCountException;
import pl.kolo.Library.webClient.catFact.dto.CatFactDto;

@Component
public class CatFactClient {
    private final RestTemplate restTemplate=new RestTemplate();

    public CatFactDto getRandomFacts(int count){
        if(count>0)
            return restTemplate.getForObject("https://meowfacts.herokuapp.com/?count="+count,CatFactDto.class);
        else throw new CatFactWrongCountException(count);
    }
}

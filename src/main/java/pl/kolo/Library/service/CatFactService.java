package pl.kolo.Library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kolo.Library.webClient.catFact.CatFactClient;
import pl.kolo.Library.webClient.catFact.dto.CatFactDto;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatFactService {

    private final CatFactClient catFactClient;
    public CatFactDto getRandomCatFact(int count) {
        return catFactClient.getRandomFacts(count);
    }

    public String getRandomCatFactAsString(int count) {
        StringBuilder result = new StringBuilder();
        CatFactDto randomFact = catFactClient.getRandomFacts(count);
        for (String datum : randomFact.getData()) {
            result.append(datum).append("\n");
        }
        return result.toString();
    }
}

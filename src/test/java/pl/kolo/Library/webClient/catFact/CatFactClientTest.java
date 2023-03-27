package pl.kolo.Library.webClient.catFact;

import org.junit.jupiter.api.Test;
import pl.kolo.Library.exception.exception.CatFactWrongCountException;
import pl.kolo.Library.webClient.catFact.dto.CatFactDto;

import static org.junit.jupiter.api.Assertions.*;

class CatFactClientTest {
    CatFactClient catFactClient=new CatFactClient();

    @Test
    void CatClient_getRandomFacts() {
        int count =5;

        CatFactDto randomFacts = catFactClient.getRandomFacts(count);
        String[] data = randomFacts.getData();

        assertNotNull(data);
        assertTrue(data.length > 0);

        for (String fact : data) {
            assertNotNull(fact);
            assertFalse(fact.isEmpty());
        }
    }

    @Test
    void CatClient_WrongSizeCount_minus5() {
        int count =-5;

        assertThrows(CatFactWrongCountException.class,()-> catFactClient.getRandomFacts(count));
    }

    @Test
    void CatClient_WrongSizeCount_0() {
        int count =0;

        assertThrows(CatFactWrongCountException.class,()-> catFactClient.getRandomFacts(count));
    }
}
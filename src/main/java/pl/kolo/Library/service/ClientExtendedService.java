package pl.kolo.Library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kolo.Library.model.Client;
import pl.kolo.Library.repository.ClientRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientExtendedService {
    private final ClientRepository clientRepository;

    public List<Client> findByBookIsNull() {
        return clientRepository.findByRentBooksIsNull();
    }

    public List<Client> createdBefore(Long minusDay) {
        return clientRepository.findByCreatedBefore(LocalDateTime.now().minusDays(minusDay));
    }

    public List<Client> createdAfter(Long minusDay) {
        return clientRepository.findByCreatedAfter(LocalDateTime.now().minusDays(minusDay));
    }

    public long myAndOnlyMyCountFivePerson() {
        return clientRepository.myAndOnlyMyCountFivePerson();
    }

    public long countClient() {
        return clientRepository.count();
    }

}

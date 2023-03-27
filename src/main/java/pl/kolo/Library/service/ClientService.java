package pl.kolo.Library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kolo.Library.model.Client;
import pl.kolo.Library.repository.ClientRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public Client updateClient(Client client) {
        Client byId = clientRepository.findById(client.getId()).orElseThrow();
        byId.setName(client.getName());
        byId.setLastName(client.getLastName());
        byId.setRentBooks(client.getRentBooks());
        return byId;
    }

    public Client getSingleClient(Long id) {
        return clientRepository.findById(id).orElseThrow();
    }

    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public List<Client> findAllClientWithBooksOneQuery() {
        return clientRepository.findAllClientWithBooksOneQuery();
    }


    public Page<Client> findAllClientWithBooksPage(int pageNumber, int pageSize) {
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return clientRepository.findAllClientWithBooksPage(pageable);
    }

    public Page<Client> findAllClientWithBooksOneQueryPage(int pageNumber, int pageSize) {
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return clientRepository.findAllClientWithBooksOneQueryPage(pageable);
    }
}

package pl.kolo.Library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kolo.Library.model.Client;
import pl.kolo.Library.model.Dto.ClientDto;
import pl.kolo.Library.service.ClientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Clients")
public class ClientController {

    private static final Long EMPTY_ID = null;
    private final ClientService clientService;

    @GetMapping("/n+1/problem")
    public List<Client> getAllClient() {
        return clientService.getAllClient();
    }

    @GetMapping("/n+1/solved")
    public List<Client> findAllClientWithBooksOneQuery() {
        return clientService.findAllClientWithBooksOneQuery();
    }

    @GetMapping("/n+1/page/problem")
    public Page<Client> findAllClientWithBooksPage(@RequestParam(required = false) Integer page
            , @RequestParam(required = false)Integer pageLimit) {

        int pageNumber = page != null && page > 0 ? page : 1;
        int pageSize = pageLimit != null && pageLimit > 0 ? pageLimit : 10;
        return clientService.findAllClientWithBooksPage(pageNumber-1,pageSize);
    }
    @GetMapping("/n+1/page/solved")
    public Page<Client> findAllClientWithBooksOneQueryPage(@RequestParam(required = false) Integer page
            , @RequestParam(required = false)Integer pageLimit) {

        int pageNumber = page != null && page > 0 ? page : 1;
        int pageSize = pageLimit != null && pageLimit > 0 ? pageLimit : 10;
        return clientService.findAllClientWithBooksOneQueryPage(pageNumber-1,pageSize);
    }


    @GetMapping("/{id}")
    public Client getSingleClient(@PathVariable Long id) {
        return clientService.getSingleClient(id);
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody ClientDto clientDto) {
        Client client = clientService.addClient(new Client(
                EMPTY_ID,
                clientDto.getName(),
                clientDto.getLastName(),
                clientDto.getCreated(),
                clientDto.getRentBooks()
        ));
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Client updateClient(@PathVariable Long id, @RequestBody ClientDto clientDto) {
        return clientService.updateClient(new Client(
                id,
                clientDto.getName(),
                clientDto.getLastName(),
                clientDto.getCreated(),
                clientDto.getRentBooks()
        ));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}

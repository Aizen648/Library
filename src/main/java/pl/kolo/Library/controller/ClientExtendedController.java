package pl.kolo.Library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kolo.Library.model.Client;
import pl.kolo.Library.service.ClientExtendedService;
import pl.kolo.Library.service.ClientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Clients")
public class ClientExtendedController {

    private final ClientExtendedService clientExtendedService;

    @GetMapping("/withoutBook")
    public List<Client> findByBookIsNull() {
        return clientExtendedService.findByBookIsNull();
    }

    @GetMapping("/createdBefore/{day}")
    public List<Client> createdBefore(@PathVariable long day) {
        return clientExtendedService.createdBefore(day);
    }

    @GetMapping("/createdAfter/{day}")
    public List<Client> createdAfter(@PathVariable long day) {
        return clientExtendedService.createdAfter(day);
    }

    @GetMapping("/count/myAndOnlyMyCountFivePerson")
    public long myAndOnlyMyCountFivePerson() {
        return clientExtendedService.myAndOnlyMyCountFivePerson();
    }

    @GetMapping("/count/client")
    public long countClient() {
        return clientExtendedService.countClient();
    }

}
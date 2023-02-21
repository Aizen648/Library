package pl.kolo.Library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kolo.Library.repository.ClientRepository;
import pl.kolo.Library.tdo.Client;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/test1")
    public int test1(){
        return 5;
    }
    @GetMapping("/test2")
    public int test2(){
        return clientRepository.test2();
    }
    @GetMapping("/")
    public List<Client> getAll(){
        return clientRepository.getAll();
    }
    @GetMapping("/{id}")
    public Client getById(@PathVariable("id") int id){
        return clientRepository.getById(id);
    }
    @PostMapping("/{id}")
    public int update(@PathVariable("id") int id,@RequestBody Client client){
        Client byId= clientRepository.getById(id);
        if(byId!=null){
            byId.setName(client.getName());
            byId.setLastName(client.getLastName());
            byId.setDateOfBirth(client.getDateOfBirth());
            byId.setCity(client.getCity());
            clientRepository.update(byId);
            return 1;
        } else {
            System.out.println("nie ma zapewne takiego id");
            return -1;
        }
    }
    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Client client) {
        Client byId = clientRepository.getById(id);
        if (byId != null) {
            if (client.getName() != null) byId.setName(client.getName());
            if (client.getLastName() != null) byId.setLastName(client.getLastName());
            if (client.getDateOfBirth() != null)byId.setDateOfBirth(client.getDateOfBirth());
            if (client.getCity() != null)byId.setCity(client.getCity());
            clientRepository.update(byId);
            return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return clientRepository.delete(id);
    }

}

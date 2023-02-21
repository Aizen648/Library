package pl.kolo.Library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.kolo.Library.repository.RentalRepository;

import java.util.List;

@RestController
public class RentalController {

    @Autowired
    RentalRepository rentalRepository;

    @GetMapping("/count/{id}")
    public int Count(@PathVariable int id){
        return rentalRepository.count(id);
    }

    @GetMapping("/borrowed/{id}")
    public List<Integer> borrowed(@PathVariable int id){
        return rentalRepository.borrowed(id);
    }

}

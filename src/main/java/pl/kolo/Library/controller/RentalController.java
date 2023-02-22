package pl.kolo.Library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kolo.Library.repository.RentalRepository;

import java.util.List;

@RestController
@RequestMapping("/rental")
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

    @PostMapping("/add/{id}")
    public int add(@PathVariable int id, @RequestBody List<Integer> idBooks){
        return rentalRepository.add(id,idBooks);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable int id){
        return rentalRepository.delete(id);
    }
}

package pl.kolo.Library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kolo.Library.repository.BookRepository;
import pl.kolo.Library.tdo.Book;

import java.util.List;


@RequestMapping("/library")
@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;


    @GetMapping("/displayAll")
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @GetMapping("/book/{id}")
    public Book getById(@PathVariable("id") int id) {
        return bookRepository.getById(id);
    }


    @PostMapping("/add")
    public int add(@RequestBody List<Book> books) {
        return bookRepository.save(books);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Book book) {
        Book byId = bookRepository.getById(id);
        if (byId != null) {
            byId.setName(book.getName());
            byId.setRaiting(book.getRaiting());
            byId.setAuthor(book.getAuthor());
            byId.setAvailable(book.isAvailable());
            bookRepository.update(byId);
            return 1;
        } else {
            System.out.println("nie ma zapewne takiego id");
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Book book) {
        Book byId = bookRepository.getById(id);
        if (byId != null) {
            if (book.getName() != null) byId.setName(book.getName());
            if (book.getRaiting() > 0) byId.setRaiting(book.getRaiting());
            if (book.getAuthor() != null)byId.setAuthor(book.getAuthor());
            byId.setAvailable(book.isAvailable());
            bookRepository.update(byId);
            return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return bookRepository.delete(id);
    }
}
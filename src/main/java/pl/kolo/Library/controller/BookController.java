package pl.kolo.Library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.kolo.Library.model.Book;
import pl.kolo.Library.model.Dto.BookDto;
import pl.kolo.Library.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private static final Long EMPTY_ID = null;
    private final BookService bookService;

    @GetMapping
    public List<Book> getAllBook() {
        return bookService.getAllBook();
    }

    @GetMapping("/available/page")
    public Page<Book> getBookPage(@RequestParam(required = false) Integer page
            , @RequestParam(required = false)Integer pageLimit) {

        int pageNumber = page != null && page > 0 ? page : 1;
        int pageSize = pageLimit != null && pageLimit > 0 ? pageLimit : 10;
        return bookService.getBookPage(pageNumber-1,pageSize);

    }


    @GetMapping("/{id}")
    public Book getAllBook(@PathVariable Long id) {
        return bookService.getSingleBook(id);
    }

    @GetMapping("/available")
    public List<Book> getAvailableBook() {
        return bookService.getAvailableBook();
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto) {
        Book book = bookService.addBook(new Book(
                EMPTY_ID,
                EMPTY_ID,
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getCreated()
        ));
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/books/{id}")
                .buildAndExpand(book.getId());
        return ResponseEntity.created(uriComponents.toUri())
                .body(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable long id , @RequestBody BookDto bookDto) {
        Book book = bookService.updateBook(new Book(
                id,
                EMPTY_ID,
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getCreated()
        ));
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/books/{id}")
                .buildAndExpand(book.getId());
        return ResponseEntity.created(uriComponents.toUri())
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}

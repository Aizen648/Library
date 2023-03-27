package pl.kolo.Library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kolo.Library.model.Book;
import pl.kolo.Library.repository.BookRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    @Transactional
    public Book updateBook(Book book) {
        Book byId = bookRepository.findById(book.getId()).orElseThrow();
        byId.setTitle(book.getTitle());
        byId.setAuthor(book.getAuthor());
        byId.setCreated(book.getCreated());
        return byId;
    }
    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }
    public Book getSingleBook(long id){ return  bookRepository.findById(id).orElseThrow();}
    public List<Book> getAvailableBook(){
        return bookRepository.findByClientIdIsNull();
    }
    public Book addBook(Book book){
        return bookRepository.save(book);
    }
    public void deleteBook(long id){ bookRepository.deleteById(id);}


    public Page<Book> getBookPage(int pageNumber, int pageSize) {
        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        return bookRepository.findAllAvailablePage(pageable);

    }
}

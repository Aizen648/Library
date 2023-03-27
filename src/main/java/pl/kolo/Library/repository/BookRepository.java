package pl.kolo.Library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.kolo.Library.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByClientIdIsNull();

    @Query("SELECT b FROM Book b where b.clientId is NULL ")
    Page<Book> findAllAvailablePage(Pageable pageable);
}

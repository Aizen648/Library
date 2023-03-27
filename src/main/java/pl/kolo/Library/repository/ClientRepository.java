package pl.kolo.Library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.kolo.Library.model.Book;
import pl.kolo.Library.model.Client;

import java.time.LocalDateTime;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {

    @Query(value = "SELECT c FROM Client c LEFT JOIN FETCH c.rentBooks", countQuery = "SELECT COUNT(c) FROM Client c")
    Page<Client> findAllClientWithBooksOneQueryPage(Pageable pageable);

    List<Client> findByRentBooksIsNull();

    List<Client> findByCreatedBefore(LocalDateTime time);

    List<Client> findByCreatedAfter(LocalDateTime time);

    @Query("Select count(*) FROM Client where id between 1 and 5")
    long myAndOnlyMyCountFivePerson();

    @Query("SELECT c FROM Client c LEFT JOIN FETCH c.rentBooks")
    List<Client> findAllClientWithBooksOneQuery();

    @Query("SELECT c FROM Client c ")
    Page<Client> findAllClientWithBooksPage(Pageable pageable);
}

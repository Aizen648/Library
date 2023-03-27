package pl.kolo.Library.model.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.kolo.Library.model.Book;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@Setter
public class ClientDto {
    private String name;
    private String lastName;
    private LocalDateTime created;
    private List<Book> rentBooks;
}


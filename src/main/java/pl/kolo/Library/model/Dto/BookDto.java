package pl.kolo.Library.model.Dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookDto {
    private String title;
    private String author;
    private LocalDateTime created;
}

package pl.kolo.Library.tdo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int id;
    private String name;
    private int raiting;
    private String author;
    private boolean available;
    @Setter(AccessLevel.NONE)
    private boolean adultsOnly;
}

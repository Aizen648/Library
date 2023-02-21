package pl.kolo.Library.tdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {
    private int id;
    private String name;
    private String lastName;
    private LocalDate dateOfBirth;
    private String city;

}

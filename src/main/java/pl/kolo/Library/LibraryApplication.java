package pl.kolo.Library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.kolo.Library.tdo.Book;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Client client=new Client("mietek","kowalski", LocalDate.of(1997,3,12),"kielce");
		Book book=new Book(214124,"ad",5,"ja",true,true);

	}
}

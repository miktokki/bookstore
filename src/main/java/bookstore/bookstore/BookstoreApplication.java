package bookstore.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import bookstore.bookstore.model.BookRepository;
import bookstore.bookstore.model.Book;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			repository.save(new Book("Jäniksen vuosi", "Arto Paasilinna", 1975, "9780132350884", 45));
			repository.save(new Book("Ihmisen lyhyt historia", "Yuval Noah Harar", 2011, "9780132350884", 55));
		};
	}

}

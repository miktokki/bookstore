package bookstore.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import bookstore.bookstore.model.BookRepository;
import bookstore.bookstore.model.Category;
import bookstore.bookstore.model.CategoryRepository;
import bookstore.bookstore.model.Book;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository) {
		return (args) -> {

			Category fiction = new Category("Fiction");
			Category horror = new Category("Horror");
			Category drama = new Category("Drama");
			Category novell = new Category("Novell");

			crepository.save(fiction);
			crepository.save(horror);
			crepository.save(drama);
			crepository.save(novell);

			repository.save(new Book("Jäniksen vuosi", "Arto Paasilinna", 1975, "9780132350884", 45, novell));
			repository.save(new Book("Ihmisen lyhyt historia", "Yuval Noah Harar", 2011, "9780132350884", 55, novell));
		};
	}

}

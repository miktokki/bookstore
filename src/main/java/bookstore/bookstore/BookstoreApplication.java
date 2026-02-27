package bookstore.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import bookstore.bookstore.model.BookRepository;
import bookstore.bookstore.model.Category;
import bookstore.bookstore.model.CategoryRepository;
import bookstore.bookstore.model.AppUser;
import bookstore.bookstore.model.AppUserRepository;
import bookstore.bookstore.model.Book;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository,
			AppUserRepository urepository) {
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

			AppUser user1 = new AppUser("user", "$2a$12$uTuY.EhLmHPX.zejOfbPKOvs8E8A8SYyM70BZ.yyet9DXSeLHiBOm", "USER");
			AppUser user2 = new AppUser("admin", "$2a$12$M9Yl/Nb2vne/zWcs.RGL2eIIRX5VlfkUZyS0K1OlYvNAJWBUryrDy",
					"ADMIN");

			urepository.save(user1);
			urepository.save(user2);
		};
	}

}

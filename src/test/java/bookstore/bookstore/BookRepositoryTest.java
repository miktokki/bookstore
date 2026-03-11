package bookstore.bookstore;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import bookstore.bookstore.model.Book;
import bookstore.bookstore.model.BookRepository;
import bookstore.bookstore.model.Category;
import bookstore.bookstore.model.CategoryRepository;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    CategoryRepository crepository;

    // SEARCH
    @Test
    public void findByTitleShouldReturnAuthor() {
        List<Book> books = repository.findByTitle("Jäniksen vuosi");

        assertThat(books).isNotEmpty();

        assertThat(books.get(0).getAuthor()).isEqualTo("Arto Paasilinna");
    }

    // CREATE
    @Test
    public void createNewBook() {
        Category category = crepository.findByName("Horror").get(0);

        Book book = new Book("Test Horror Book", "Stephen King", 1990, "TESTISBN123", 25, category);

        repository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    // DELETE
    @Test
    public void DeleteBook() {
        Category category = crepository.findByName("Drama").get(0);

        Book book = new Book("TestiTitle", "TestiAuthor", 2000, "DELETEISBN", 10, category);

        book = repository.save(book);
        Long id = book.getId();

        repository.deleteById(id);

        assertThat(repository.findById(id)).isEmpty();

    }
}

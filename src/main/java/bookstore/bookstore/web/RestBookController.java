package bookstore.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bookstore.bookstore.model.Book;
import bookstore.bookstore.model.BookRepository;

@RestController
@RequestMapping("/api/books")
public class RestBookController {

    private BookRepository bookRepository;

    public RestBookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // RESTful service to get all books
    @GetMapping
    public List<Book> bookListRest() {
        return (List<Book>) bookRepository.findAll();
    }

    // RESTful service to get book by id
    @GetMapping("/{id}")
    public Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
        return bookRepository.findById(bookId);
    }

    // RESTful service to save new book
    @PostMapping
    public Book saveNewBookRest(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Book replaceBook(@PathVariable Long id, @RequestBody Book newBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(newBook.getTitle());
                    book.setAuthor(newBook.getAuthor());
                    book.setPublicationYear(newBook.getPublicationYear());
                    book.setIsbn(newBook.getIsbn());
                    book.setPrice(newBook.getPrice());
                    book.setCategory(newBook.getCategory());
                    return bookRepository.save(book);
                })
                .orElseGet(() -> {
                    return bookRepository.save(newBook);
                });

    }

    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}

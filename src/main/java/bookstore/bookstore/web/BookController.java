package bookstore.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import bookstore.bookstore.model.Book;
import bookstore.bookstore.model.BookRepository;

@Controller
public class BookController {

    private BookRepository repository;

    // constructor injection - works only if only one constructor
    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = { "/", "/books" })
    public String getBooks(Model model) {

        // bookRepository.findAll -- Sql select * from book
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

}

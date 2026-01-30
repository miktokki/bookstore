package bookstore.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @PostMapping(value = { "/save" })
    public String saveBook(@ModelAttribute Book book) {
        repository.save(book);
        return "redirect:/books";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/books";

    }

}

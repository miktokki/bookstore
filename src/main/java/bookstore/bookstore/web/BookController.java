package bookstore.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import bookstore.bookstore.model.Book;
import bookstore.bookstore.model.BookRepository;
import bookstore.bookstore.model.CategoryRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository crepository;

    /*
     * // constructor injection - works only if only one constructor
     * public BookController(BookRepository repository) {
     * this.repository = repository;
     * }
     */

    @GetMapping(value = { "/", "/books" })
    public String getBooks(Model model) {

        // bookRepository.findAll -- Sql select * from book
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", crepository.findAll());
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

    @GetMapping(value = "/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", repository.findById(id).orElseThrow());
        model.addAttribute("categories", crepository.findAll());
        return "editbook";

    }

}

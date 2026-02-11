package kevat26.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kevat26.bookstore.domain.Book;
import kevat26.bookstore.domain.BookRepository;
import kevat26.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    // constructor injection, only one constructor!
    public BookController(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/index")
    public String showIndex() {
        return "index";
    }

    @GetMapping("/*")
    public String showIndex2() {
        return "index";
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }

    @GetMapping("/addBook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    @PostMapping("/saveBook")
    public String addBook(Book book, Model model) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        bookRepository.deleteById(bookId);
        return "redirect:../books";
    }

    @GetMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        model.addAttribute("book", bookRepository.findById(bookId));
        model.addAttribute("categories", categoryRepository.findAll());
        return "editbook";
    }

}

/*
 * Bookstore: One-to-many
 * Use your own bookstore application from the GitHub.
 * 
 * Check StudentList example from the moodle (Student – Department
 * relationship).
 * 
 * Add new entity called Category which has attributes id and name
 * Add one-to-many relationship between Book and Category
 * Add new category attribute to the Book entity
 * Add new books attribute to the Category entity (List of books)
 * Add getters and setters for new attributes
 * Add annotations for one-to-many relationship
 * Add crud repository class for the Category entity
 * Add Category dropdown list to the book creation form
 * Add categories to controller model
 * Show category name in the dropdown list
 * Add Category into book listpage to show category name of the book
 * Insert few categories to database by using CommandLineRunner
 */

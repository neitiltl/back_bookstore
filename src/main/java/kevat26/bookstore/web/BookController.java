package kevat26.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kevat26.bookstore.domain.Book;
import kevat26.bookstore.domain.BookRepository;

@Controller

public class BookController {

    private BookRepository bookRepository;

    // constructor injection, only one constructor!
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
        return "editbook";
    }

}

/*
 * Edit Bookstore
 * Add edit functionality to your bookstore. Create edit link after delete link
 * to your listpage. Edit link will open current book in the edit page.
 * 
 * Tip. Check ‘Add book’-functionality from the controller. In add functionality
 * you added new book object to model but now you will add current book object
 * to model. You also have to send current book id from the list page to
 * controller (like you did in delete link).
 */

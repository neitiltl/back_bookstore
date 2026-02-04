package kevat26.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kevat26.bookstore.domain.BookRepository;

@Controller
@ResponseBody

public class BookController {

    private BookRepository bookRepository;

    // constructor injection, only one constructor!
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/index")
    public String showIndex() {
        return "Bookstore index";
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books";

    }

}

/*
 * 5. Bookstore
 * 
 * Create a project called Bookstore
 * 
 * Add a new controller called BookController which handle get request to the
 * path /index
 * 
 * Add a new model class called Book which contains attributes: title, author,
 * publicationYear, isbn, price
 * 
 */

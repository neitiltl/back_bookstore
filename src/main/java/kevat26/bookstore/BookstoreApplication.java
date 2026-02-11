package kevat26.bookstore;

import kevat26.bookstore.domain.CategoryRepository;
import kevat26.bookstore.domain.Category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import kevat26.bookstore.domain.Book;
import kevat26.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	private final CategoryRepository categoryRepository;

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	BookstoreApplication(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			log.info("save books");

			Category category1 = new Category("Fakta");
			Category category2 = new Category("Fiktio");

			categoryRepository.save(category1);
			categoryRepository.save(category2);

			bookRepository.save(
					new Book("Hömppää hempeille", "Jaakko Virtanen", "1925", "978-0-7432-7356-5", "12.99", category1));

			bookRepository.save(
					new Book("Mättöä machoille", "Jatta Korhonen", "1960", "978-0-06-112008-4", "14.99", category2));

			bookRepository.save(
					new Book("Taattua laatua", "Jaska Jokunen", "1948", "978-0-452-28423-4", "13.99", category2));

			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}

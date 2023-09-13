package com.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.Bookstore.model.Book;
import com.Bookstore.model.BookRepository;
import com.Bookstore.model.Category;
import com.Bookstore.model.CategoryRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository ) {
		return (args) -> {
			log.info("save categories");
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Dystopian"));
			crepository.save(new Category("Sci-fi"));
			log.info("save a couple of books");
			repository.save(new Book("A Farewell to Arms", "Ernest Hemingway", 1929, "1232323-21", 20.00, crepository.findByName("Horror").get(0)));
			repository.save(new Book("Animal Farm", "George Orwell", 1945, "2212343-5", 25.00, crepository.findByName("Dystopian").get(0))); 

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
				
			log.info("fetch all categories");
			for (Category category : crepository.findAll()) {
				log.info(category.toString());
			}
			}

		};
	}

}

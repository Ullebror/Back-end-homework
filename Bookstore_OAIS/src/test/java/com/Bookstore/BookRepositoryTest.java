package com.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.Bookstore.model.Book;
import com.Bookstore.model.BookRepository;
import com.Bookstore.model.Category;
import com.Bookstore.model.CategoryRepository;

@DataJpaTest
public class BookRepositoryTest {
	@Autowired
	private BookRepository repository;

	@Autowired
	private CategoryRepository crepository;

	@Test
	public void findByTitleShouldReturnAuthor() {
		List<Book> books = repository.findByTitle("Animal Farm");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("George Orwell");

	}

	@Test
	public void findByAuthorShouldReturnTitle() {
		List<Book> books = repository.findByAuthor("George Orwell");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("Animal Farm");

	}

	@Test
	public void findByPublicationYearShouldReturnTitle() {
		List<Book> books = repository.findByPublicationYear(1945);
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("Animal Farm");

	}

	@Test
	public void createNewBook() {
		Category category = new Category("Fantasy");
		crepository.save(category);
		Book book = new Book("A Game Of Thrones", "George RR Martin", 1996, "1232323-28", 20.00,
				new Category("Fantasy"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}

	@Test
	public void deleteNewBook() {
		List<Book> books = repository.findByTitle("A Farewell to Arms");
		Book book = books.get(0);
		repository.delete(book);
		List<Book> newBooks = repository.findByTitle("A Farewell to Arms");
		assertThat(newBooks).hasSize(0);
	}

}

package com.ampta.quickstart.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.ampta.quickstart.TestDataUtil;
import com.ampta.quickstart.domain.Author;
import com.ampta.quickstart.domain.Book;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTest {
	
	private BookRepository underTest;
	private AuthorRepository authorRepository;

	@Autowired
	public BookRepositoryIntegrationTest(BookRepository underTest, AuthorRepository authorRepository) {
		this.underTest = underTest;
		this.authorRepository = authorRepository;
	}
	
	@BeforeEach
	public void setup() {
	    // Clear existing records if needed
	    underTest.deleteAll(); // Assuming `underTest` is the repository
	}
	
	@Test
	public void testThatBookCanBeCreatedAndRecalled() {
		
		Author authorA = TestDataUtil.createTestAuthorA();
		log.info("Author created for test: {}", authorA);
		authorRepository.save(authorA);

		Book bookA = TestDataUtil.createTestBookA(authorA);
		log.info("Book created for test: {}", bookA);
		
		underTest.save(bookA);
		
		Optional<Book> result = underTest.findById(bookA.getIsbn());
		
		log.info("Book from database: {}", result);
		
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(bookA);
		
	}
	
	@Test
	public void testThatMultipleBooksCanBeCreatedAndRecalled() {
		Author authorA = TestDataUtil.createTestAuthorA();
		authorRepository.save(authorA);
		
		Book bookA = TestDataUtil.createTestBookA(authorA);
		underTest.save(bookA);
		
		Book bookC = TestDataUtil.createTestBookC(authorA);
		underTest.save(bookC);
		
		Book bookB = TestDataUtil.createTestBookB(authorA);
		underTest.save(bookB);
		
		
		
		Iterable<Book> result = underTest.findAll();
		log.info("List of Books: {}", result.toString());
		
		assertThat(result)
			.hasSize(3)
			.containsExactly(bookA, bookC, bookB);
		
	}
	
	
	@Test
	public void testThatBookCanBeUpdated() {
		Author authorA = TestDataUtil.createTestAuthorA();
		authorRepository.save(authorA);
		
		Book bookA = TestDataUtil.createTestBookA(authorA);
		underTest.save(bookA);
		
		log.info("Book for test: {}", bookA);
		
		bookA.setTitle("UPDATED");
		underTest.save(bookA);
		
		Optional<Book> result = underTest.findById(bookA.getIsbn());
		
		log.info("Book after Update: {}", result.get());
		
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(bookA);
	}
	
	
	@Test
	public void testThatBookCanBeDeleted() {
		Author authorA = TestDataUtil.createTestAuthorA();
		authorRepository.save(authorA);
		
		Book bookA = TestDataUtil.createTestBookA(authorA);
		underTest.save(bookA);
		
		underTest.deleteById(bookA.getIsbn());
		
		Optional<Book> result = underTest.findById(bookA.getIsbn());
		
		assertThat(result).isEmpty();
	}

}

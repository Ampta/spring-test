//package com.ampta.quickstart.repositories;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.annotation.DirtiesContext.ClassMode;
//
//import com.ampta.quickstart.TestDataUtil;
//import com.ampta.quickstart.dao.AuthorDao;
//import com.ampta.quickstart.domain.Author;
//import com.ampta.quickstart.domain.Book;
//
//import lombok.extern.slf4j.Slf4j;
//
//@SpringBootTest
//@Slf4j
//@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
//public class BookDaoImplIntegrationTest {
//	
//	private AuthorDao authorDao;
//	private BookDaoImpl underTest;
//
//	@Autowired
//	public BookDaoImplIntegrationTest(BookDaoImpl underTest, AuthorDao authorDao) {
//		this.underTest = underTest;
//		this.authorDao = authorDao;
//	}
//	
//	@Test
//	public void testThatBookCanBeCreatedAndRecalled() {
//		
//		Author author = TestDataUtil.createTestAuthor();
//		authorDao.create(author);
//		
//		Book book = TestDataUtil.createTestBook();
//		book.setAuthorId(author.getId());
//		log.info("Book for test: {}", book);
//		
//		underTest.create(book);
//		
//		Optional<Book> result = underTest.findByIsbn(book.getIsbn());
//		
//		log.info("Book from database: {}", result);
//		
//		assertThat(result).isPresent();
//		assertThat(result.get()).isEqualTo(book);
//		
//		
//	}
//	
//	@Test
//	public void testThatMultipleBooksCanBeCreatedAndRecalled() {
//		Author author = TestDataUtil.createTestAuthor();
//		authorDao.create(author);
//		
//		Book bookA = TestDataUtil.createTestBookA();
//		bookA.setAuthorId(author.getId());
//		underTest.create(bookA);
//		
//		Book bookC = TestDataUtil.createTestBookC();
//		bookC.setAuthorId(author.getId());
//		underTest.create(bookC);
//		
//		Book bookB = TestDataUtil.createTestBookB();
//		bookB.setAuthorId(author.getId());
//		underTest.create(bookB);
//		
//		
//		
//		List<Book> result = underTest.findMany();
//		log.info("List of Books: {}", result.toString());
//		
//		assertThat(result)
//			.hasSize(3)
//			.containsExactly(bookA, bookC, bookB);
//		
//	}
//	
//	
//	@Test
//	public void testThatBookCanBeUpdated() {
//		Author author = TestDataUtil.createTestAuthor();
//		authorDao.create(author);
//		
//		Book bookA = TestDataUtil.createTestBookA();
//		bookA.setAuthorId(author.getId());
//		underTest.create(bookA);
//		log.info("Book for test: {}", bookA);
//		bookA.setTitle("UPDATED");
//		underTest.update(bookA.getIsbn(), bookA);
//		
//		Optional<Book> result = underTest.findByIsbn(bookA.getIsbn());
//		log.info("Book after Update: {}", result.get());
//		assertThat(result).isPresent();
//		assertThat(result.get()).isEqualTo(bookA);
//	}
//	
//	@Test
//	public void testThatBookCanBeDeleted() {
//		Author author = TestDataUtil.createTestAuthor();
//		authorDao.create(author);
//		
//		Book bookA = TestDataUtil.createTestBookA();
//		bookA.setAuthorId(author.getId());
//		underTest.create(bookA);
//		
//		underTest.delete(bookA.getIsbn());
//		
//		Optional<Book> result = underTest.findByIsbn(bookA.getIsbn());
//		
//		assertThat(result).isEmpty();
//	}
//
//}

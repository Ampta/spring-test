package com.ampta.quickstart.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ampta.quickstart.TestDataUtil;
import com.ampta.quickstart.dao.AuthorDao;
import com.ampta.quickstart.domain.Author;
import com.ampta.quickstart.domain.Book;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
public class BookDaoImplIntegrationTest {
	
	private AuthorDao authorDao;
	private BookDaoImpl underTest;

	@Autowired
	public BookDaoImplIntegrationTest(BookDaoImpl underTest, AuthorDao authorDao) {
		this.underTest = underTest;
		this.authorDao = authorDao;
	}
	
	@Test
	public void testThatBookCanBeCreatedAndRecalled() {
		
		Author author = TestDataUtil.createTestAuthor();
		authorDao.create(author);
		
		Book book = TestDataUtil.createTestBook();
		book.setAuthorId(author.getId());
		log.info("Book for test: {}", book);
		
		underTest.create(book);
		
		Optional<Book> result = underTest.findByIsbn(book.getIsbn());
		
		log.info("Book from database: {}", result);
		
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(book);
		
		
	}
	
	

}

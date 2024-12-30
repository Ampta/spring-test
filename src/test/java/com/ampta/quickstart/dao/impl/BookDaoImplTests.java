package com.ampta.quickstart.dao.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ampta.quickstart.TestDataUtil;
import com.ampta.quickstart.dao.impl.BookDaoImpl;
import com.ampta.quickstart.domain.Book;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {

	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private BookDaoImpl underTest;
	
	@Test
	public void testThatCreateBookGeneratesCurrectSql() {
		Book book = TestDataUtil.createTestBook();
		
		underTest.create(book);
		
		verify(jdbcTemplate).update(
				eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"), 
				eq("898-1-4744-4373-5"),
				eq("Anjali Changed my Life"),
				eq(1L)
		);
	}

	@Test
	public void testThatFindOneBookGeneratesCurrectSql() {
		underTest.findByIsbn("898-1-4744-4373-5");
		
		verify(jdbcTemplate).query(
				eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"), 
				any(BookDaoImpl.BookRowMapper.class),
				eq("898-1-4744-4373-5")
		);
	}
	
	@Test
	public void testThatFindManyGeneratesCurrectSql() {
		underTest.findMany();
		
		verify(jdbcTemplate).query(
					eq("SELECT isbn, title, author_id FROM books"), 
					any(BookDaoImpl.BookRowMapper.class)
		);
	}
	
	@Test
	public void testThatUpdateGeneratesCurrectSql() {
		Book book = TestDataUtil.createTestBook();
		underTest.update("898-1-4744-4373-5", book);
		
		verify(jdbcTemplate).update(
				"UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
				"898-1-4744-4373-5", "Anjali Changed my Life", 1L, "898-1-4744-4373-5"
		);
	}
	
	
	@Test
	public void testThatDeleteGeneratesCurrectSql() {
		underTest.delete("898-1-4744-4373-5");
		
		verify(jdbcTemplate).update(
					"DELETE FROM books WHERE isbn = ?",
					"898-1-4744-4373-5"
		);
	}
}

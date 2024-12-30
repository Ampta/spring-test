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
import com.ampta.quickstart.domain.Author;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class AuthorDaoImplTests {
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private AuthorDaoImpl underTest;
	
	@Test
	public void testThatCreateAuthorGeneratesCurrectSql() {
		
		Author author = TestDataUtil.createTestAuthor();
		log.info("Author for test: {}", author);
		underTest.create(author);
		
		verify(jdbcTemplate).update(
				eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
				eq(1L), eq("Nakanu Gupta"), eq(20)
				);
		
		log.info("CreateAuthor Successfully executed");
	}

	@Test
	public void testThatFindOneGeneratesCurrectSql() {
		underTest.findOne(1L);
		 
		verify(jdbcTemplate).query(
				eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"), 
				any(AuthorDaoImpl.AuthorRowMapper.class),
				eq(1L));
		
		log.info("FindAuthor Successfully executed");
	}
	
	@Test
	public void testThatFindManyGeneratesCurrectSql() {
		underTest.findMany();
		
		verify(jdbcTemplate).query(
			eq("SELECT id, name, age FROM authors"), 
			any(AuthorDaoImpl.AuthorRowMapper.class));
	}
	
	
	@Test
	public void testThatUpdateGeneratesCurrectSql() {
		
		Author author = TestDataUtil.createTestAuthor();
		
		underTest.update(2L, author);
		
		verify(jdbcTemplate).update(
					"UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
					1L, "Nakanu Gupta", 20, 2L
				);
	}
	
	
	@Test
	public void testThatDeleteGeneratesCurrectSql() {
		underTest.delete(1L);
		verify(jdbcTemplate).update(
				"DELETE FROM authors WHERE id = ?",
				1L);
	}
}

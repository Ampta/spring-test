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

import com.ampta.quickstart.domain.Author;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private AuthorDaoImpl underTest;
	
	@Test
	public void testThatCreateAuthorGeneratesCurrectSql() {
		
		Author author = Author.builder()
				.id(1L)
				.name("Shivam Gupta")
				.age(20)
				.build();
		
		
		underTest.create(author);
		
		verify(jdbcTemplate).update(
				eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
				eq(1L), eq("Shivam Gupta"), eq(20)
				);
	}
	
	@Test
	public void testThatFindOneAuthorGeneratesCurrectSql() {
		underTest.findOne(1L);
		
		verify(jdbcTemplate).query(
				eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"), 
				any(AuthorDaoImpl.AuthorRowMapper.class),
				eq(1L));
	}
	
}

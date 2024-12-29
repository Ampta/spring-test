package com.ampta.quickstart.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ampta.quickstart.dao.AuthorDao;
import com.ampta.quickstart.domain.Author;

public class AuthorDaoImpl implements AuthorDao {
	
	private final JdbcTemplate jdbcTemplate;

	public AuthorDaoImpl(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void create(Author author) {
		jdbcTemplate.update(
				"INSERT INTO authors (id, name, age) VALUES (?, ?, ?)",
				author.getId(), author.getName(), author.getAge()
		);
	}

	
	
	
	
}

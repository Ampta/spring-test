package com.ampta.quickstart.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ampta.quickstart.dao.AuthorDao;

public class AuthorDaoImpl implements AuthorDao {
	
	private final JdbcTemplate jdbcTemplate;

	public AuthorDaoImpl(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	
}

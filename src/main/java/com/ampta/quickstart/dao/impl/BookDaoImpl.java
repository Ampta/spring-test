package com.ampta.quickstart.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ampta.quickstart.dao.BookDao;

public class BookDaoImpl implements BookDao{

	private final JdbcTemplate jdbcTemplate;

	public BookDaoImpl(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
}

package com.ampta.quickstart.dao;

import java.util.List;
import java.util.Optional;

import com.ampta.quickstart.domain.Book;

public interface BookDao {
	void create(Book book);
	
	Optional<Book> findByIsbn(String isbn);
	
	List<Book> findMany();
}

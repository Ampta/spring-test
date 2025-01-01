package com.ampta.quickstart.services;

import java.util.List;
import java.util.Optional;

import com.ampta.quickstart.domain.entity.BookEntity;

public interface BookService {
	BookEntity createBook(String isbn, BookEntity bookEntity);

	List<BookEntity> findAll();

	Optional<BookEntity> findOne(String isbn);

	boolean isExists(String isbn);

	BookEntity partialUpdate(String isbn, BookEntity bookEntity);

	void delete(String isbn);

}

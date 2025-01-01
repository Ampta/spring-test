package com.ampta.quickstart.services;

import java.util.List;

import com.ampta.quickstart.domain.entity.BookEntity;

public interface BookService {
	BookEntity createBook(String isbn, BookEntity bookEntity);

	List<BookEntity> findAll();
}

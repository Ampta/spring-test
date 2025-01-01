package com.ampta.quickstart.services;

import com.ampta.quickstart.domain.entity.BookEntity;

public interface BookService {
	BookEntity createBook(String isbn, BookEntity bookEntity);
}

package com.ampta.quickstart.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ampta.quickstart.domain.dto.BookDto;
import com.ampta.quickstart.domain.entity.BookEntity;
import com.ampta.quickstart.mappers.Mapper;
import com.ampta.quickstart.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	private Mapper<BookEntity, BookDto> bookMapper;
	
	private BookService bookService;
	
	public BookController(Mapper<BookEntity, BookDto> bookMapper, BookService bookService) {
		this.bookMapper = bookMapper;
		this.bookService = bookService;
	}



	@PutMapping("/{isbn}")
	public ResponseEntity<BookDto> createBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto){
		
		BookEntity bookEntity = bookMapper.mapFrom(bookDto);
		
		BookEntity savedBookEntity = bookService.createBook(isbn, bookEntity);
		
		BookDto savedBookDto = bookMapper.mapTo(savedBookEntity);
		
		return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
	}

}

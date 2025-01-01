package com.ampta.quickstart.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
	public ResponseEntity<BookDto> createUpdateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto){
		BookEntity bookEntity = bookMapper.mapFrom(bookDto);
		
		boolean  bookExists = bookService.isExists(isbn);
		
		BookEntity savedBookEntity = bookService.createBook(isbn, bookEntity);
		
		BookDto savedBookDto = bookMapper.mapTo(savedBookEntity);
		
//		return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
		
		if(bookExists) {
			 // Update the Book
			return new ResponseEntity<>(savedBookDto, HttpStatus.OK); 
		}else {
			// Create the Book
			return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED); 
		}
		
	}
	
	
	@GetMapping
	public List<BookDto> listOfBooks(){
		List<BookEntity> books = bookService.findAll();
		
		return books.stream()
				.map(bookMapper:: mapTo)
				.collect(Collectors.toList());
		
	}
	
	@GetMapping("/{isbn}")
	public ResponseEntity<BookDto> getBookByIsbn(@PathVariable("isbn") String isbn){
		
		Optional<BookEntity> foundBook = bookService.findOne(isbn);
		return foundBook.map(bookEntity -> {
			BookDto bookDto = bookMapper.mapTo(bookEntity);
			return new ResponseEntity<>(bookDto, HttpStatus.OK);
		}).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	
	@PatchMapping("/{isbn}")
	public ResponseEntity<BookDto> partialUpdateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto){
		
		
		boolean  bookExists = bookService.isExists(isbn);
		if(!bookExists) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		BookEntity bookEntity = bookMapper.mapFrom(bookDto);
		BookEntity updatedBookEntity = bookService.partialUpdate(isbn, bookEntity);
		
		return new ResponseEntity<>(bookMapper.mapTo(updatedBookEntity), HttpStatus.OK);
	}

}

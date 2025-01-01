package com.ampta.quickstart.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ampta.quickstart.domain.dto.AuthorDto;
import com.ampta.quickstart.domain.entity.AuthorEntity;
import com.ampta.quickstart.mappers.Mapper;
import com.ampta.quickstart.services.AuthorService;

@RestController
@RequestMapping("authors")
public class AuthorController {
	
	private AuthorService authorService;
	
	private Mapper<AuthorEntity, AuthorDto> authorMapper;

	public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
		this.authorService = authorService;
		this.authorMapper = authorMapper;
	}


	@PostMapping
	public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto author) {
		
		AuthorEntity authorEntity = authorMapper.mapFrom(author);
		AuthorEntity savedAuthorEntity = authorService.createAuthor(authorEntity);
		
		return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);

	}
}

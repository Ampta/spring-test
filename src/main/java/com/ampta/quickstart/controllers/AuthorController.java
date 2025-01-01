package com.ampta.quickstart.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		AuthorEntity savedAuthorEntity = authorService.save(authorEntity);
		
		return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);

	}
	
	@GetMapping
	public List<AuthorDto> listOfAuthors(){
		
		List<AuthorEntity> authors = authorService.findAll();
		
		
		return authors.stream().map(authorMapper::mapTo).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AuthorDto> getAuthorById(@PathVariable("id") Long id){
		
		Optional<AuthorEntity> foundAuthor = authorService.findOne(id);
		
		return foundAuthor.map(authorEntity -> {
			AuthorDto authorDto = authorMapper.mapTo(authorEntity);
			return new ResponseEntity<>(authorDto, HttpStatus.OK);
		}).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AuthorDto> fullUpdateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto){
		
		if(!authorService.isExists(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		authorDto.setId(id);
		AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
		AuthorEntity savedAuthorEntity = authorService.save(authorEntity);
		
		return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<AuthorDto> partialUpdateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto){
		if(!authorService.isExists(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
		AuthorEntity updatedAuthorEntity = authorService.partialUpdate(id, authorEntity);
		
		return new ResponseEntity<>(authorMapper.mapTo(updatedAuthorEntity), HttpStatus.OK);
	}
}

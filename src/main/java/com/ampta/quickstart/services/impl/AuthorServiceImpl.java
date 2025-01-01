package com.ampta.quickstart.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.ampta.quickstart.domain.entity.AuthorEntity;
import com.ampta.quickstart.repositories.AuthorRepository;
import com.ampta.quickstart.services.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	private AuthorRepository authorRepository;
	
	public AuthorServiceImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public AuthorEntity createAuthor(AuthorEntity authorEntity) {
		return authorRepository.save(authorEntity);
	}

	@Override
	public List<AuthorEntity> findAll() {
		return StreamSupport.stream(authorRepository
				.findAll()
				.spliterator(), 
				false)
			.collect(Collectors.toList());
		
	}

	@Override
	public Optional<AuthorEntity> findOne(Long id) {
		return authorRepository.findById(id);
	}




}

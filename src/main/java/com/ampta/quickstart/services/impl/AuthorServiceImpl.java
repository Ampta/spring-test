package com.ampta.quickstart.services.impl;

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


}

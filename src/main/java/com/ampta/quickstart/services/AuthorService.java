package com.ampta.quickstart.services;

import java.util.List;
import java.util.Optional;

import com.ampta.quickstart.domain.entity.AuthorEntity;

public interface AuthorService {
	
	AuthorEntity createAuthor(AuthorEntity author);

	List<AuthorEntity> findAll();

	Optional<AuthorEntity> findOne(Long id);
	
	
	

}

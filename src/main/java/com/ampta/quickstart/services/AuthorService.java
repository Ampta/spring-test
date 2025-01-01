package com.ampta.quickstart.services;

import java.util.List;

import com.ampta.quickstart.domain.entity.AuthorEntity;

public interface AuthorService {
	
	AuthorEntity createAuthor(AuthorEntity author);

	List<AuthorEntity> findAll();
	
	
	

}

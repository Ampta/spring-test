package com.ampta.quickstart.services;

import java.util.List;
import java.util.Optional;

import com.ampta.quickstart.domain.entity.AuthorEntity;

public interface AuthorService {
	
	AuthorEntity save(AuthorEntity author);

	List<AuthorEntity> findAll();

	Optional<AuthorEntity> findOne(Long id);

	boolean isExists(Long id);

	AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity);

	void delete(Long id);
	
	
	

}

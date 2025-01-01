package com.ampta.quickstart.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ampta.quickstart.domain.entity.AuthorEntity;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long>{

	
	Iterable<AuthorEntity> ageLessThan(int age);
	
	@Query("SELECT a FROM AuthorEntity a where a.age > ?1")
	Iterable<AuthorEntity> findAuthorsWithAgeGreaterThan(int age);
}


package com.ampta.quickstart.dao;

import java.util.List;
import java.util.Optional;

import com.ampta.quickstart.domain.Author;

public interface AuthorDao {
	
	void create(Author author);
	
	Optional<Author> findOne(long authorId);
	
	List<Author> findMany();
}

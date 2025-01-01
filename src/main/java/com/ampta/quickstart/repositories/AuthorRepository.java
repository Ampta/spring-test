package com.ampta.quickstart.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ampta.quickstart.domain.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long>{

}

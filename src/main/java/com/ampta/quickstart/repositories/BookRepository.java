package com.ampta.quickstart.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ampta.quickstart.domain.entity.BookEntity;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, String>{

}

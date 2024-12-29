package com.ampta.quickstart.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ampta.quickstart.TestDataUtil;
import com.ampta.quickstart.domain.Author;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
public class AuthorDaoImplIntegrationTest {
	
	private AuthorDaoImpl underTest;
	
	@Autowired
	public AuthorDaoImplIntegrationTest(AuthorDaoImpl underTest) {
		this.underTest = underTest;
	}



	@Test
	public void testThatAuthorCanBeCreatedAndRecalled() {
		Author author = TestDataUtil.createTestAuthor();
		log.info("Author for test: {}", author);
		
		underTest.create(author);
		Optional<Author> result = underTest.findOne(author.getId());
		
		log.info("Author from database: {}", result);
		
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(author);
	}

}

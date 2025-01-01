package com.ampta.quickstart.repositories;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.ampta.quickstart.TestDataUtil;
import com.ampta.quickstart.domain.entity.AuthorEntity;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTest {
	
	private AuthorRepository underTest;
	
	@Autowired
	public AuthorRepositoryIntegrationTest(AuthorRepository underTest) {
		this.underTest = underTest;
	}
	
	@BeforeEach
	public void setup() {
	    // Clear existing records if needed
	    underTest.deleteAll(); // Assuming `underTest` is the repository
	}

//	@Test
	public void testThatAuthorCanBeCreatedAndRecalled() {
		AuthorEntity authorA = TestDataUtil.createTestAuthorA();
		log.info("Author for test: {}", authorA);
		
		underTest.save(authorA);
		Optional<AuthorEntity> result = underTest.findById(authorA.getId());
		
		log.info("Author from database: {}", result);
		
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(authorA);
	}

	
//	@Test
	public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
		AuthorEntity authorA = TestDataUtil.createTestAuthorA();
		underTest.save(authorA);
		
		AuthorEntity authorB = TestDataUtil.createTestAuthorB();
		underTest.save(authorB);
		
		AuthorEntity authorC = TestDataUtil.createTestAuthorC();
		underTest.save(authorC);
		
		Iterable<AuthorEntity> result = underTest.findAll();
		
		log.info("List of authors: {}", result);
		
		assertThat(result)
			.hasSize(3)
			.containsExactly(authorA, authorB, authorC);
		
	} 
	
//	@Test 
	public void testThatAuthorCanBeUpdated() {
		AuthorEntity authorA = TestDataUtil.createTestAuthorA();
		underTest.save(authorA);
		authorA.setName("UPDATED");
		underTest.save(authorA);
		
		Optional<AuthorEntity> result = underTest.findById(authorA.getId());
		
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(authorA );
		
		
	}
	
//	@Test 
	public void testThatAuthorCanBeDeleted() {
		AuthorEntity authorA = TestDataUtil.createTestAuthorA();
		underTest.save(authorA);
		
		underTest.deleteById(authorA.getId());
		
		Optional<AuthorEntity> result = underTest.findById(authorA.getId());
		assertThat(result).isEmpty();
	}
	
	@Test
	public void testThatGetAuthorsWithAgeLessThan() {
		AuthorEntity authorA = TestDataUtil.createTestAuthorA();
		underTest.save(authorA);
		
		AuthorEntity authorB = TestDataUtil.createTestAuthorB();
		underTest.save(authorB);
		
		AuthorEntity authorC = TestDataUtil.createTestAuthorC();
		underTest.save(authorC);
		
		int age = 26;
		Iterable<AuthorEntity> result = underTest.ageLessThan(age);
		
		log.info("Age Less than {} authors are: {}", age, result);
		
		assertThat(result).containsExactly(authorA, authorB);
	}
	
	@Test
	public void testThatGetAuthorsWithAgeGreaterThan() {
		AuthorEntity authorA = TestDataUtil.createTestAuthorA();
		underTest.save(authorA);
		
		AuthorEntity authorB = TestDataUtil.createTestAuthorB();
		underTest.save(authorB);
		
		AuthorEntity authorC = TestDataUtil.createTestAuthorC();
		underTest.save(authorC);
		
		int age = 100;
		Iterable<AuthorEntity> result = underTest.findAuthorsWithAgeGreaterThan(age);
		
		log.info("Age Greater than {} authors are: {}", age, result);
		
		assertThat(result).containsExactly(authorC);
		
	}

}

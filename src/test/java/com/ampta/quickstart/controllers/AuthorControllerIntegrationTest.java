package com.ampta.quickstart.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ampta.quickstart.TestDataUtil;
import com.ampta.quickstart.domain.dto.AuthorDto;
import com.ampta.quickstart.domain.entity.AuthorEntity;
import com.ampta.quickstart.repositories.AuthorRepository;
import com.ampta.quickstart.services.AuthorService;
import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@Slf4j
public class AuthorControllerIntegrationTest {
	
	private MockMvc mockMvc;

	private ObjectMapper objectMapper;
	
	private AuthorService authorService;
	
	@Autowired
	public AuthorControllerIntegrationTest(MockMvc mockMvc, 
			ObjectMapper objectMapper, 
			AuthorRepository authorRepository, AuthorService authorService) {
		this.mockMvc = mockMvc;
		this.objectMapper = new ObjectMapper();
		this.authorService = authorService;
	}
	
	@Test
	public void testThatCreateAuthorReturnsHTTP201Created() throws Exception {
		
		AuthorEntity testAuthor = TestDataUtil.createTestAuthorA();
		testAuthor.setId(null);
		String authorJson = objectMapper.writeValueAsString(testAuthor);
		
		
		mockMvc.perform(
					MockMvcRequestBuilders.post("/authors")
						.contentType(MediaType.APPLICATION_JSON)
						.content(authorJson)
				).andExpect(
					MockMvcResultMatchers.status().isCreated()
				);
	}
	
	@Test
	public void testThatCreateAuthorSuccessfullyReturnedSavedAuthor() throws Exception {
		
		AuthorEntity testAuthor = TestDataUtil.createTestAuthorA();
		testAuthor.setId(null);
		String authorJson = objectMapper.writeValueAsString(testAuthor);
		
		
		mockMvc.perform(
					MockMvcRequestBuilders.post("/authors")
						.contentType(MediaType.APPLICATION_JSON)
						.content(authorJson)
				).andExpect(
					MockMvcResultMatchers.jsonPath("$.id").isNumber()
				).andExpect(
						MockMvcResultMatchers.jsonPath("$.name").value("Shivam Gupta")
				).andExpect(
						MockMvcResultMatchers.jsonPath("$.age").value(25)
				);
	}
	
	@Test
	public void testThatListAuthorsReturnsHTTP200Ok() throws Exception {
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/authors")
					.contentType(MediaType.APPLICATION_JSON)
			).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	public void testThatListAuthorsReturnsListOfAuthors() throws Exception {
		
		AuthorEntity testAuthor = TestDataUtil.createTestAuthorA();
		authorService.save(testAuthor);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/authors")
					.contentType(MediaType.APPLICATION_JSON)
			).andExpect(
				MockMvcResultMatchers.jsonPath("$[1].id").isNumber()
			).andExpect(
					MockMvcResultMatchers.jsonPath("$[1].name").value("Shivam Gupta")
			).andExpect(
					MockMvcResultMatchers.jsonPath("$[1].age").value(25)
			);
	}
	
	
	@Test
	public void testThatGetAuthorReturnsHTTP200WhenAuthorExists() throws Exception {
		
		AuthorEntity testAuthor = TestDataUtil.createTestAuthorA();
		authorService.save(testAuthor);
		
		Long authorId = testAuthor.getId();
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/authors/" + authorId)
					.contentType(MediaType.APPLICATION_JSON)
			).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testThatGetAuthorReturnsAuthorWhenAuthorExists() throws Exception {
		
		AuthorEntity testAuthor = TestDataUtil.createTestAuthorA();
		authorService.save(testAuthor);
		
		Long authorId = testAuthor.getId();
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/authors/" + authorId)
					.contentType(MediaType.APPLICATION_JSON)
			).andExpect(
				MockMvcResultMatchers.jsonPath("$.id").value(authorId)
			).andExpect(
				MockMvcResultMatchers.jsonPath("$.name").value("Shivam Gupta")
			).andExpect(
				MockMvcResultMatchers.jsonPath("$.age").value(25)
			);
	}

	@Test
	public void testThatGetAuthorReturnsHTTP404WhenAuthorNotExists() throws Exception {
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/authors/99")
					.contentType(MediaType.APPLICATION_JSON)
			).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void testThatFullUpdateAuthorReturnsHTTP404WhenAuthorNotExists() throws Exception {
		AuthorDto testAuthorDto = TestDataUtil.createTestAuthor();
		String testJosn = objectMapper.writeValueAsString(testAuthorDto);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/authors/99")
					.contentType(MediaType.APPLICATION_JSON)
					.content(testJosn)
			).andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	
	@Test
	public void testThatFullUpdateAuthorReturnsHTTP200WhenAuthorExists() throws Exception {
		AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
		AuthorEntity savedAuthor = authorService.save(authorEntity);
		
		AuthorDto testAuthorDto = TestDataUtil.createTestAuthor();
		String testJosn = objectMapper.writeValueAsString(testAuthorDto);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/authors/" + savedAuthor.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(testJosn)
			).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test 
	public void testThatFullUpdateUpdatesExistingAuthor() throws Exception {
		AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
		AuthorEntity savedAuthor = authorService.save(authorEntity);
		
		AuthorEntity authorDto = TestDataUtil.createTestAuthorB();
		authorDto.setId(savedAuthor.getId());
		
		String testJson = objectMapper.writeValueAsString(authorDto);
		
		mockMvc.perform(
				MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(testJson)
			).andExpect(
					MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
			).andExpect(
				    MockMvcResultMatchers.jsonPath("$.name").value(authorDto.getName())
			).andExpect(
					MockMvcResultMatchers.jsonPath("$.age").value(authorDto.getAge())
			);
		
	}
	
	
	@Test
	public void testThatPartialUpdateReturnsHTTP200WhenAuthorExists() throws Exception {
		AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
		AuthorEntity savedAuthor = authorService.save(authorEntity);
		
		AuthorDto testAuthorDto = TestDataUtil.createTestAuthor();
		testAuthorDto.setName("New Name");
		String testJosn = objectMapper.writeValueAsString(testAuthorDto);
		
		mockMvc.perform(
				MockMvcRequestBuilders.patch("/authors/" + savedAuthor.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(testJosn)
			).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testThatPartialUpdateReturnsUpdatedAuthor() throws Exception {
		AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
		AuthorEntity savedAuthor = authorService.save(authorEntity);
		
		AuthorDto testAuthorDto = TestDataUtil.createTestAuthor();
		testAuthorDto.setName("New Name");
		String testJosn = objectMapper.writeValueAsString(testAuthorDto);
		
		mockMvc.perform(
				MockMvcRequestBuilders.patch("/authors/" + savedAuthor.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(testJosn)
			).andExpect(
					MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
			).andExpect(
				    MockMvcResultMatchers.jsonPath("$.name").value("New Name")
			).andExpect(
					MockMvcResultMatchers.jsonPath("$.age").value(testAuthorDto.getAge())
			);
	}
	
	 
}

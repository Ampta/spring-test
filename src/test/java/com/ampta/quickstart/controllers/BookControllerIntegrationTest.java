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
import com.ampta.quickstart.domain.dto.BookDto;
import com.ampta.quickstart.domain.entity.BookEntity;
import com.ampta.quickstart.services.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {
	
	private MockMvc mockMvc;

	private ObjectMapper objectMapper;
	
	private BookService bookService;
	
	@Autowired
	public BookControllerIntegrationTest(MockMvc mockMvc, 
			ObjectMapper objectMapper,
			BookService bookService) {
		this.mockMvc = mockMvc;
		this.objectMapper = new ObjectMapper();
		this.bookService = bookService;
	}
	
	@Test
	public void testThatCreateBookReturnsHTTP201Created() throws Exception {
		BookDto bookDto = TestDataUtil.createTestBookDto(null);
		
		String bookJson = objectMapper.writeValueAsString(bookDto);
		
		mockMvc.perform(
				MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
					.contentType(MediaType.APPLICATION_JSON)
					.content(bookJson)
			).andExpect(
				MockMvcResultMatchers.status().isCreated()
			);
		
	}
	
	@Test
	public void testThatCreateBookReturnsSavedBook() throws Exception {
		BookDto bookDto = TestDataUtil.createTestBookDto(null);
		
		String bookJson = objectMapper.writeValueAsString(bookDto);
		
		mockMvc.perform(
				MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
					.contentType(MediaType.APPLICATION_JSON)
					.content(bookJson)
			).andExpect(
					MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
			).andExpect(
					MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
			);
		
	}
	
	@Test
	public void testThatListBooksReturnsHTTP200Ok() throws Exception {
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/books")
					.contentType(MediaType.APPLICATION_JSON)
			).andExpect(
				MockMvcResultMatchers.status().isOk()
			);
		
	}
	
	@Test
	public void testThatListBookReturnsBook() throws Exception {
		
		BookEntity bookEntity = TestDataUtil.createTestBookA(null);
		bookService.createBook(bookEntity.getIsbn(), bookEntity);
		
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/books")
					.contentType(MediaType.APPLICATION_JSON)
			).andExpect(
					MockMvcResultMatchers.jsonPath("$[0].isbn").value("198-9-5749-4373-0")
			).andExpect(
					MockMvcResultMatchers.jsonPath("$[0].title").value("wHo tELe WhoM")
			);
	}
	
	
	@Test
	public void testThatGetBookReturnsHTTP200WhenBookExists() throws Exception {
		
		BookEntity bookEntity = TestDataUtil.createTestBookA(null);
		bookService.createBook(bookEntity.getIsbn(), bookEntity);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/books/" + bookEntity.getIsbn())
					.contentType(MediaType.APPLICATION_JSON)
			).andExpect(
				MockMvcResultMatchers.status().isOk()
			);
	}
	
	@Test
	public void testThatGetBookReturnsBookWhenBookExists() throws Exception {
		
		BookEntity bookEntity = TestDataUtil.createTestBookA(null);
		bookService.createBook(bookEntity.getIsbn(), bookEntity);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/books/" + bookEntity.getIsbn())
					.contentType(MediaType.APPLICATION_JSON)
			).andExpect(
					MockMvcResultMatchers.jsonPath("$.isbn").value(bookEntity.getIsbn())
			).andExpect(
					MockMvcResultMatchers.jsonPath("$.title").value("YOU EXIST BECAUSE OF ME")
			);
	}
	
	@Test
	public void testThatGetBookReturnsHTTP200WhenBookNotExists() throws Exception {
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/books/88")
					.contentType(MediaType.APPLICATION_JSON)
			).andExpect(
				MockMvcResultMatchers.status().isNotFound()
			);
	}
}

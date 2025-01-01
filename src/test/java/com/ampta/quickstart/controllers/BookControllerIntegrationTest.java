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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {
	
	private MockMvc mockMvc;

	private ObjectMapper objectMapper;
	
	@Autowired
	public BookControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper) {
		this.mockMvc = mockMvc;
		this.objectMapper = new ObjectMapper();
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
}

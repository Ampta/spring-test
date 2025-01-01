package com.ampta.quickstart;

import com.ampta.quickstart.domain.dto.AuthorDto;
import com.ampta.quickstart.domain.dto.BookDto;
import com.ampta.quickstart.domain.entity.AuthorEntity;
import com.ampta.quickstart.domain.entity.BookEntity;

public final class TestDataUtil {
	
	private TestDataUtil() {}

	 
	public static AuthorDto createTestAuthor() {
		return AuthorDto.builder()
				.name("Anjali Gupta")
				.age(25)
				.build();
	}
	
	public static AuthorEntity createTestAuthorA() {
		return AuthorEntity.builder()
				.name("Shivam Gupta")
				.age(25)
				.build();
	}
	
	public static AuthorEntity createTestAuthorB() {
		return AuthorEntity.builder()
				.name("Logic Gupta")
				.age(20)
				.build();
	}
	
	public static AuthorEntity createTestAuthorC() {
		return AuthorEntity.builder()
				.name("Ampta Gupta")
				.age(200)
				.build();
	}

	
	public static BookEntity createTestBookA(final AuthorEntity author) {
		return BookEntity.builder()
				.isbn("021-1-0000-4373-6")
				.title("YOU EXIST BECAUSE OF ME")
				.author(author)
				.build();
	}
	
	public static BookEntity createTestBookB(final AuthorEntity author) {
		return BookEntity.builder()
				.isbn("198-9-5749-4373-0")
				.title("wHo tELe WhoM")
				.author(author)
				.build();
	}
	
	public static BookEntity createTestBookC(final AuthorEntity author) {
		return BookEntity.builder()
				.isbn("100-1-0000-0000-5")
				.title("The Long Day in Night")
				.author(author)
				.build();
	}
	
	public static BookDto createTestBookDto(final AuthorEntity author) {
		return BookDto.builder()
				.isbn("198-9-5749-4373-0")
				.title("wHo tELe WhoM")
				.author(author)
				.build();
	}
	
	public static BookDto createTestBookDtoTest(final AuthorEntity author) {
		return BookDto.builder()
				.isbn("198-9-5749-4373-0")
				.title("wHo tELe WhoM")
				.author(author)
				.build();
	}

}

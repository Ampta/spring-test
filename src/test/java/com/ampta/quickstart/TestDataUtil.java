package com.ampta.quickstart;

import com.ampta.quickstart.domain.Author;
import com.ampta.quickstart.domain.Book;

public final class TestDataUtil {
	
	private TestDataUtil() {}
	
	public static Author createTestAuthor() {
		return Author.builder()
				.id(1L)
				.name("Nakanu Gupta")
				.age(20)
				.build();
	}

	public static Author createTestAuthorA() {
		return Author.builder()
				.id(1L)
				.name("Shivam Gupta")
				.age(20)
				.build();
	}
	
	public static Author createTestAuthorB() {
		return Author.builder()
				.id(2L)
				.name("Logic Gupta")
				.age(20)
				.build();
	}
	
	public static Author createTestAuthorC() {
		return Author.builder()
				.id(3L)
				.name("Ampta Gupta")
				.age(20)
				.build();
	}

	public static Book createTestBook() {
		return Book.builder()
				.isbn("898-1-4744-4373-5")
				.title("Anjali Changed my Life")
				.authorId(1L)
				.build();
	}
	
	public static Book createTestBookA() {
		return Book.builder()
				.isbn("021-1-0000-4373-6")
				.title("YOU EXIST BECAUSE OF ME")
				.authorId(1L)
				.build();
	}
	
	public static Book createTestBookB() {
		return Book.builder()
				.isbn("198-9-5749-4373-0")
				.title("wHo tELe WhoM")
				.authorId(1L)
				.build();
	}
	
	public static Book createTestBookC() {
		return Book.builder()
				.isbn("100-1-0000-0000-5")
				.title("The Long Day in Night")
				.authorId(1L)
				.build();
	}
	
	

}

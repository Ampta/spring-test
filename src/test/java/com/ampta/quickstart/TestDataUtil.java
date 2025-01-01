package com.ampta.quickstart;

import com.ampta.quickstart.domain.Author;
import com.ampta.quickstart.domain.Book;

public final class TestDataUtil {
	
	private TestDataUtil() {}

	public static Author createTestAuthorA() {
		return Author.builder()
				.name("Shivam Gupta")
				.age(25)
				.build();
	}
	
	public static Author createTestAuthorB() {
		return Author.builder()
				.name("Logic Gupta")
				.age(20)
				.build();
	}
	
	public static Author createTestAuthorC() {
		return Author.builder()
				.name("Ampta Gupta")
				.age(200)
				.build();
	}

	
	public static Book createTestBookA(final Author author) {
		return Book.builder()
				.isbn("021-1-0000-4373-6")
				.title("YOU EXIST BECAUSE OF ME")
				.author(author)
				.build();
	}
	
	public static Book createTestBookB(final Author author) {
		return Book.builder()
				.isbn("198-9-5749-4373-0")
				.title("wHo tELe WhoM")
				.author(author)
				.build();
	}
	
	public static Book createTestBookC(final Author author) {
		return Book.builder()
				.isbn("100-1-0000-0000-5")
				.title("The Long Day in Night")
				.author(author)
				.build();
	}
	
	

}

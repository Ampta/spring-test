package com.ampta.quickstart;

import com.ampta.quickstart.domain.Author;
import com.ampta.quickstart.domain.Book;

public final class TestDataUtil {
	
	private TestDataUtil() {}

	public static Author createTestAuthor() {
		return Author.builder()
				.id(1L)
				.name("Shivam Gupta")
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
	
	

}

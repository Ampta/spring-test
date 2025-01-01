package com.ampta.quickstart.domain.dto;

import com.ampta.quickstart.domain.entity.AuthorEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
	
	private String isbn;
	private String title;
	private AuthorEntity author; 
}

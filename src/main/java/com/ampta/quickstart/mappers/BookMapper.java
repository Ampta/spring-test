package com.ampta.quickstart.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ampta.quickstart.domain.dto.BookDto;
import com.ampta.quickstart.domain.entity.BookEntity;

@Component
public class BookMapper implements Mapper<BookEntity, BookDto>{
	
	private ModelMapper modelMapper;

	public BookMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public BookDto mapTo(BookEntity bookEntity) {
		return modelMapper.map(bookEntity, BookDto.class);
	}

	@Override
	public BookEntity mapFrom(BookDto bookDto) {
		return modelMapper.map(bookDto, BookEntity.class);
	}

}

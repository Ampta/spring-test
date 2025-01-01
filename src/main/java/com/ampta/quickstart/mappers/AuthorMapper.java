package com.ampta.quickstart.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ampta.quickstart.domain.dto.AuthorDto;
import com.ampta.quickstart.domain.entity.AuthorEntity;


@Component
public class AuthorMapper implements Mapper<AuthorEntity, AuthorDto>{

	private ModelMapper modelMapper;
	
	public AuthorMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public AuthorDto mapTo(AuthorEntity authorEntiry) {
		return modelMapper.map(authorEntiry, AuthorDto.class);
	}

	@Override
	public AuthorEntity mapFrom(AuthorDto authorDto) {
		return modelMapper.map(authorDto, AuthorEntity.class);
	}
	
	

}

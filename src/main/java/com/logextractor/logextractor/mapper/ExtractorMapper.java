package com.logextractor.logextractor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.logextractor.logextractor.models.Extractor;
import com.logextractor.logextractor.models.ExtractorDto;

@Mapper(componentModel = "spring")
public interface ExtractorMapper {

	ExtractorMapper INSTANCE = Mappers.getMapper(ExtractorMapper.class);
	
	Extractor extractorDtoToExtractor(ExtractorDto extractorDto);
	
	ExtractorDto extractorToExtractorDto(Extractor extractor);
}

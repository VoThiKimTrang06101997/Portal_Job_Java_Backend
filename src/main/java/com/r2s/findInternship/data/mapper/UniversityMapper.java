package com.r2s.findInternship.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.r2s.findInternship.data.dto.UniversityDTO;
import com.r2s.findInternship.data.entity.University;

@Mapper(componentModel = "spring", uses = { StatusMapper.class, UniversityTypeMapper.class})
public interface UniversityMapper {
	
	@Mapping(source = "universityTypeDTO", target = "universityType")
	@Mapping(source = "statusDTO", target = "status")
	University toEntity(UniversityDTO universityDTO);

	@Mapping(source = "universityType", target = "universityTypeDTO")
	@Mapping(source = "status", target = "statusDTO")
	UniversityDTO toDTO(University university);

}

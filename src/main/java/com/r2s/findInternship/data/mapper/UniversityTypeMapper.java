package com.r2s.findInternship.data.mapper;

import org.mapstruct.Mapper;

import com.r2s.findInternship.data.dto.UniversityTypeDTO;
import com.r2s.findInternship.data.entity.UniversityType;

@Mapper(componentModel = "spring")
public interface UniversityTypeMapper {
	UniversityType toEntity(UniversityTypeDTO universityTypeDTO);

	UniversityTypeDTO toDTO(UniversityType universityType);
}

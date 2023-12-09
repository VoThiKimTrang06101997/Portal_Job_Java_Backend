package com.r2s.findInternship.data.mapper;

import org.mapstruct.Mapper;

import com.r2s.findInternship.data.dto.HRApplicationDTO;
import com.r2s.findInternship.data.entity.HRApplication;

@Mapper(componentModel = "spring")
public interface HRApplicationMapper {
	HRApplication toEntity(HRApplicationDTO hrApplicationDTO);

	HRApplicationDTO toDTO(HRApplication hrApplication);
}

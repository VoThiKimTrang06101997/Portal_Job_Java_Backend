package com.r2s.findInternship.data.mapper;

import org.mapstruct.Mapper;

import com.r2s.findInternship.data.dto.StatusDTO;
import com.r2s.findInternship.data.entity.Status;
@Mapper(componentModel = "spring")
public interface StatusMapper {
	Status toEntity(StatusDTO statusDTO);

	StatusDTO toDTO(Status status);
}

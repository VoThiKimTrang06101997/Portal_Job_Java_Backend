package com.r2s.findInternship.data.mapper;

import com.r2s.findInternship.data.dto.InternshipPositionDTO;
import com.r2s.findInternship.data.entity.InternshipPosition;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InternshipPositionMapper {
    InternshipPosition toEntity(InternshipPositionDTO jobPositionDemandDTO);

    // @Mapping(source = "jobPosition", target = "jobPositionDTO")
    InternshipPositionDTO toDTO(InternshipPosition jobPositionDemand);

}

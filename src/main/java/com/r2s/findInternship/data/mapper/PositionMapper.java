package com.r2s.findInternship.data.mapper;

import com.r2s.findInternship.data.dto.PositionDTO;
import com.r2s.findInternship.data.entity.CandidatePosition;
import com.r2s.findInternship.data.entity.JobPosition;
import com.r2s.findInternship.data.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    Position toEntity(PositionDTO positionDTO);

    PositionDTO toDTO(Position position);

    @Mapping(source = "position.id", target = "id")
    @Mapping(source = "position.name", target = "name")
    PositionDTO toPositionDTO(CandidatePosition candidatePosition);

    @Mapping(source = "position.id", target = "id")
    @Mapping(source = "position.name", target = "name")
    PositionDTO jobPositiontoPositionDto(JobPosition jobPosition);

}


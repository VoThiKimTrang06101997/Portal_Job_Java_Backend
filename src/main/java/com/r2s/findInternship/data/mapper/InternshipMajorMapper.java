package com.r2s.findInternship.data.mapper;

import com.r2s.findInternship.data.dto.InternshipMajorDTO;
import com.r2s.findInternship.data.entity.InternshipMajor;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { MajorMapper.class })
public interface InternshipMajorMapper {
    InternshipMajor toEntity(InternshipMajorDTO majorDemandDTO);

    @Mapping(source = "major", target = "majorDTO")
    InternshipMajorDTO toDTO(InternshipMajor majorDemand);

}

package com.r2s.findInternship.data.mapper;

import com.r2s.findInternship.data.entity.CandidateMajor;
import com.r2s.findInternship.data.entity.JobMajor;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import com.r2s.findInternship.data.dto.MajorDTO;
import com.r2s.findInternship.data.entity.Major;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MajorMapper {

	Major toEntity(MajorDTO majorDTO);

	@Mapping(source = "major.id", target = "id")
    @Mapping(source = "major.name", target = "name")
    MajorDTO toMajorDTO(CandidateMajor candidateMajor);

	@Mapping(source = "major.id", target = "id")
	@Mapping(source = "major.name", target = "name")
	MajorDTO jobMajortoMajorDto(JobMajor jobMajor);
	@IterableMapping(elementTargetType = MajorDTO.class)
	List<MajorDTO> jobMajortoMajorDto(List<JobMajor> jobMajors);


	MajorDTO toDTO(Major major);

}

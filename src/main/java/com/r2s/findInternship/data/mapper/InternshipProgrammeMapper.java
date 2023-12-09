package com.r2s.findInternship.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.r2s.findInternship.data.dto.InternshipProgrammeDTO;
import com.r2s.findInternship.data.dto.show.UniversityDemandDTOShow;
import com.r2s.findInternship.data.entity.InternshipProgramme;

@Mapper(componentModel = "spring", uses = { PartnerMapper.class, InternshipPositionMapper.class,
		InternshipScheduleMapper.class, InternshipMajorMapper.class, StatusMapper.class })
public interface InternshipProgrammeMapper {
	InternshipProgramme toEntity(InternshipProgrammeDTO internshipProgrammeDTO);

	InternshipProgrammeDTO toDTO(InternshipProgramme internshipProgramme);


//	 @Mapping(source = "jobPositionDemandDTOs", target = "internshipPositions")
//	 @Mapping(source = "jobTypeDemandDTOs", target = "internshipSchedules")
//	 @Mapping(source = "majorDemandDTOs", target = "internshipMajors")
//	 @Mapping(source = "status", target = "statusDTO")

	 @Mapping(source = "internshipPositions", target = "jobPositionDemandDTOs")
	 @Mapping(source = "internshipSchedules", target = "jobTypeDemandDTOs")
	 @Mapping(source = "internshipMajors", target = "majorDemandDTOs")
	 @Mapping(source = "status", target = "statusDTO")
	UniversityDemandDTOShow toDTOShow(InternshipProgramme internshipProgramme);

}

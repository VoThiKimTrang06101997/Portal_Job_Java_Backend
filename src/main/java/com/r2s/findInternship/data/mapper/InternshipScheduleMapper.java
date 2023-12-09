package com.r2s.findInternship.data.mapper;

import com.r2s.findInternship.data.dto.InternshipScheduleDTO;
import com.r2s.findInternship.data.entity.InternshipSchedule;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ScheduleMapper.class })
public interface InternshipScheduleMapper {

    InternshipScheduleDTO toEntity(InternshipScheduleDTO jobTypeDemandDTO);

    // @Mapping(source = "jobType", target = "jobTypeDTO")
    InternshipScheduleDTO toDTO(InternshipScheduleDTO jobTypeDemand);

}

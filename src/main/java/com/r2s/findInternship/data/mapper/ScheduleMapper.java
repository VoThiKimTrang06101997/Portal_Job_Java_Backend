package com.r2s.findInternship.data.mapper;

import com.r2s.findInternship.data.entity.CandidateSchedule;
import com.r2s.findInternship.data.entity.JobSchedule;
import org.mapstruct.Mapper;

import com.r2s.findInternship.data.dto.ScheduleDTO;
import com.r2s.findInternship.data.entity.Schedule;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

	Schedule toEntity(ScheduleDTO scheduleDTO);

	ScheduleDTO toDTO(Schedule schedule);

	@Mapping(source = "schedule.id", target = "id")
    @Mapping(source = "schedule.name", target = "name")
    ScheduleDTO toScheduleDTO(CandidateSchedule candidateSchedule);

	@Mapping(source = "schedule.id", target = "id")
	@Mapping(source = "schedule.name", target = "name")
	ScheduleDTO toDTO(JobSchedule jobSchedule);

}

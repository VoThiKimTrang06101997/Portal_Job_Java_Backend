package com.r2s.findInternship.data.mapper;

import com.r2s.findInternship.data.dto.JobShowDTO;
import org.mapstruct.Mapper;

import com.r2s.findInternship.data.dto.JobCreationDTO;
import com.r2s.findInternship.data.dto.JobDTO;
import com.r2s.findInternship.data.dto.show.JobDTOShow;
import com.r2s.findInternship.data.entity.Job;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", uses = { HRMapper.class, PositionMapper.class,
        ScheduleMapper.class, MajorMapper.class, StatusMapper.class , CompanyMapper.class})
public interface JobMapper {

    @Mapping(source = "companyDTO", target = "company")
    Job toEntity(JobCreationDTO jobCreationDTO);
//    @Mapping(source = "companyDTO", target = "company")
//	 @Mapping(source = "majorDTOs", target = "jobMajors")
//	 @Mapping(source = "positionDTOs", target = "jobPositions")
//	 @Mapping(source = "scheduleDTOs", target = "jobSchedules")
////	 @Mapping(source = "locationDTO", target = "location")
//	 @Mapping(source = "statusDTO", target = "status")
//	Job toEntity(JobDTO jobDTO);

    @Mapping(source = "companyDTO", target = "company")
    @Mapping(source = "statusDTO", target = "status")
    Job jobUpdateDTOToJob(JobDTO jobDTO);


    @Mapping(source = "company", target = "companyDTO")
    @Mapping(source = "status", target = "statusDTO")
    @Mapping(source = "jobMajors", target = "majorDTOs")
    @Mapping(source = "jobPositions", target = "positionDTOs")
    @Mapping(source = "jobSchedules", target = "scheduleDTOs")
    @Mapping(expression = "java(job.getCandidateApplications().size())", target = "numOfApplication")
    JobDTO toDTO(Job job);

    //	 @Mapping(source = "hr.id", target = "hrId")
//	 @Mapping(source = "hr.user.firstName", target = "hrFirstName")
//	 @Mapping(source = "hr.user.lastName", target = "hrLastName")
//	 @Mapping(expression = "java(job.getCandidateApplications().size())", target = "amountOfAppliedCandidates")
//	 @Mapping(source = "location", target = "locationDTO")
//	 @Mapping(source = "status.name", target = "status")
    @Mapping(source = "company", target = "companyDTO")
    @Mapping(source = "jobMajors", target = "majorDTOs")
    @Mapping(source = "jobPositions", target = "positionDTOs")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "salaryMin", target = "salaryMin")
    @Mapping(source = "salaryMax", target = "salaryMax")
    @Mapping(source = "requirement", target = "requirement")
    @Mapping(source = "otherInfo", target = "otherInfo")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "location", target = "location")
    JobShowDTO toDTOShow(Job job);

    List<JobShowDTO> toDtoList(List<Job> jobs);

}

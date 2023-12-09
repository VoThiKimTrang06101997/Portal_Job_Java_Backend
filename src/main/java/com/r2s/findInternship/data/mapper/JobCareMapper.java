package com.r2s.findInternship.data.mapper;

import com.r2s.findInternship.data.dto.JobCareDTO;
import com.r2s.findInternship.data.entity.JobCare;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = JobMapper.class)
public interface JobCareMapper {
    @Mapping(source = "candidateDTO",target = "candidate")
    @Mapping(source = "jobDTO",target = "job")
    JobCare toEntity(JobCareDTO jobCareDTO);
    
    @Mapping(source = "job",target = "jobDTO")
    @Mapping(source = "candidate", target = "candidateDTO" )
    JobCareDTO toDTO(JobCare jobCare);
}

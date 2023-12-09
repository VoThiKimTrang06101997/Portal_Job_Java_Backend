package com.r2s.findInternship.service;

import java.util.List;

import com.r2s.findInternship.common.MessageResponse;
import com.r2s.findInternship.data.dto.JobCareDTO;
import com.r2s.findInternship.data.dto.PaginationDTO;

public interface JobCareService {

    PaginationDTO findAllByCandidateId(long candidateId, int no, int limit);

    List<Integer> findJobSaveOfCandidateID(long candidateId);

    PaginationDTO findAllByJobId(int jobId, int no, int limit);

    List<JobCareDTO> findAll();

    JobCareDTO findById(int id);

    JobCareDTO findByCandidateIdAndJobId(int candidateId, int jobId);

    MessageResponse create(long idJob);

//    JobCareDTO update(int id, JobCareDTO jobCareDTO);

    MessageResponse deleteById(long idJobCare);

}

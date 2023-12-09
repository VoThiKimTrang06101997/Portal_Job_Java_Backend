package com.r2s.findInternship.service;

import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.data.dto.CandidateApplicationDTO;
import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.entity.CandidateApplication;

public interface CandidateApplicationService {

	PaginationDTO findAll(int no, int limit);

	PaginationDTO findAllByCandidateId(long candidateId, int no, int limit);

	PaginationDTO findAllByJobId(long jobId, int no, int limit);

	CandidateApplicationDTO findById(long id);

	CandidateApplication getById(long id);

    CandidateApplicationDTO readJson(String value, MultipartFile fileCV);

    boolean existsByJobIdAndCandidateId(long jobId, long candidateId);

	CandidateApplicationDTO create(CandidateApplicationDTO candidateApplicationDTO);

	CandidateApplicationDTO update(long id, CandidateApplicationDTO candidateApplicationDTO);

	boolean deleteById(long id);

}

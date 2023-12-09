package com.r2s.findInternship.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.common.MessageResponse;
import com.r2s.findInternship.data.dto.CandidateFilterByHRDTO;
import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.dto.candidate.CandidateCreationDTO;
import com.r2s.findInternship.data.dto.candidate.CandidateDTO;
import com.r2s.findInternship.data.dto.candidate.CandidateProfileDTO;

public interface CandidateService {

    boolean isCurrentAuthor(Long id);

    void activeCandidate(String token);
    @Transactional
    CandidateDTO create(CandidateCreationDTO candidateCreationDTO, MultipartFile fileAvatar);
    
    @Transactional
    CandidateDTO update(long id, CandidateProfileDTO candidateProfileDTO, MultipartFile fileAvatar, MultipartFile fileCV);

    CandidateDTO findByUserId(long userId);

    CandidateDTO findById(long id);

    MessageResponse updateSearchable(long id);

    PaginationDTO filterByHR(CandidateFilterByHRDTO candidateFilterByHRDTO, int no, int limit);

}


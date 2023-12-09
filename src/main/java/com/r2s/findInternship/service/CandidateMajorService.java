package com.r2s.findInternship.service;

import java.util.List;

import com.r2s.findInternship.data.dto.MajorDTO;

public interface CandidateMajorService {
    boolean update(long candidateId, List<MajorDTO> majorDTOs);
}

package com.r2s.findInternship.service;

import java.util.List;

import com.r2s.findInternship.data.dto.PositionDTO;

public interface CandidatePositionService {
    boolean update(long candidateId, List<PositionDTO> positionDTOs);
}

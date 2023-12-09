package com.r2s.findInternship.service;

import java.util.List;

import com.r2s.findInternship.data.dto.ScheduleDTO;

public interface CandidateScheduleService {
    boolean update(long candidateId, List<ScheduleDTO> scheduleDTOs);
}

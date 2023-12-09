package com.r2s.findInternship.service;

import com.r2s.findInternship.common.MessageResponse;
import com.r2s.findInternship.data.dto.MajorDTO;
import com.r2s.findInternship.data.dto.PositionDTO;

import java.util.List;

public interface PositionService {
    PositionDTO findById(Integer id);

    List<PositionDTO> findAll();

    MessageResponse create(PositionDTO positionDTO);

    MessageResponse deleteById(Integer id);
}

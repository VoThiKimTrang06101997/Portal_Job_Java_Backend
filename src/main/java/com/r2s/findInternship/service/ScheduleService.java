package com.r2s.findInternship.service;

import com.r2s.findInternship.common.MessageResponse;
import com.r2s.findInternship.data.dto.ScheduleDTO;

import java.util.List;

public interface ScheduleService {

    ScheduleDTO findById(Integer id);

    List<ScheduleDTO> findAll();

    MessageResponse create(ScheduleDTO scheduleDTO);


    MessageResponse deleteById(Integer id);
}

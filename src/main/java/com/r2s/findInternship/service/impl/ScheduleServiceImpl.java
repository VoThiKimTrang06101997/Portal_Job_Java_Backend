package com.r2s.findInternship.service.impl;

import com.r2s.findInternship.common.MessageResponse;
import com.r2s.findInternship.data.dto.PositionDTO;
import com.r2s.findInternship.data.dto.ScheduleDTO;
import com.r2s.findInternship.data.entity.Position;
import com.r2s.findInternship.data.entity.Schedule;
import com.r2s.findInternship.data.mapper.ScheduleMapper;
import com.r2s.findInternship.data.repository.ScheduleRepository;
import com.r2s.findInternship.exception.InternalServerErrorException;
import com.r2s.findInternship.exception.ResourceNotFoundException;
import com.r2s.findInternship.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public ScheduleDTO findById(Integer id) {
        return null;
    }

    @Override
    public List<ScheduleDTO> findAll() {
        return this.scheduleRepository.findAll().stream().map(item -> this.scheduleMapper.toDTO(item))
                .collect(Collectors.toList());
    }

    @Override
    public MessageResponse create(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleMapper.toEntity(scheduleDTO);
        if (scheduleRepository.existsByName(schedule.getName()))
            throw new InternalServerErrorException(
                    String.format("Exists major named %s", schedule.getName()));
        scheduleRepository.save(schedule);
        return new MessageResponse(200, null, null);
    }

    @Override
    public MessageResponse deleteById(Integer id) {
        this.scheduleRepository.delete(this.scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Major", "id",
                        String.valueOf(id))));
        return new MessageResponse(200, null, null);
    }
}

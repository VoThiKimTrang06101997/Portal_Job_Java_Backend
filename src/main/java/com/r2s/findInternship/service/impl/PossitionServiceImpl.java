package com.r2s.findInternship.service.impl;

import com.r2s.findInternship.common.MessageResponse;
import com.r2s.findInternship.data.dto.PositionDTO;
import com.r2s.findInternship.data.entity.Major;
import com.r2s.findInternship.data.entity.Position;
import com.r2s.findInternship.data.mapper.PositionMapper;
import com.r2s.findInternship.data.repository.PositionRepository;
import com.r2s.findInternship.exception.InternalServerErrorException;
import com.r2s.findInternship.exception.ResourceNotFoundException;
import com.r2s.findInternship.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PossitionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private PositionMapper positionMapper;

    @Override
    public PositionDTO findById(Integer id) {
        return null;
    }

    @Override
    public List<PositionDTO> findAll() {
        return this.positionRepository.findAll().stream().map(item -> this.positionMapper.toDTO(item))
                .collect(Collectors.toList());
    }

    @Override
    public MessageResponse create(PositionDTO positionDTO) {
        Position position = positionMapper.toEntity(positionDTO);
        if (positionRepository.existsByName(position.getName()))
            throw new InternalServerErrorException(
                    String.format("Exists major named %s", position.getName()));
        positionRepository.save(position);
        return new MessageResponse(200, null, null);
    }

    @Override
    public MessageResponse deleteById(Integer id) {
        this.positionRepository.delete(this.positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Major", "id",
                        String.valueOf(id))));
        return new MessageResponse(200, null, null);
    }
}

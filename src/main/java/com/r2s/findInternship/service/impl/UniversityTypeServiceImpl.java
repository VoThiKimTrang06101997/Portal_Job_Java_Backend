package com.r2s.findInternship.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2s.findInternship.data.dto.UniversityTypeDTO;
import com.r2s.findInternship.data.entity.UniversityType;
import com.r2s.findInternship.data.mapper.UniversityTypeMapper;
import com.r2s.findInternship.data.repository.UniversityTypeRepository;
import com.r2s.findInternship.exception.ResourceNotFoundException;
import com.r2s.findInternship.service.UniversityTypeService;

@Service
public class UniversityTypeServiceImpl implements UniversityTypeService {
	@Autowired
	private UniversityTypeRepository universityTypeRepository;
	@Autowired
	private UniversityTypeMapper universityTypeMapper;

	@Override
	public UniversityTypeDTO create(UniversityType universityType) {
		return this.universityTypeMapper.toDTO(this.universityTypeRepository.save(universityType));
	}

	@Override
	public List<UniversityTypeDTO> findAll() {
		return universityTypeRepository.findAll().stream().map(item -> this.universityTypeMapper.toDTO(item))
				.collect(Collectors.toList());
	}

	@Override
	public UniversityTypeDTO findById(Integer id) {
		UniversityType universityType = this.universityTypeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("University type", "id", String.valueOf(id)));
		return this.universityTypeMapper.toDTO(universityType);
	}

	@Override
	public boolean existsById(Integer id) {
		return universityTypeRepository.existsById(id);
	}

}

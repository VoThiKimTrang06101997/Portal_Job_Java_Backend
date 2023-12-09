package com.r2s.findInternship.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2s.findInternship.data.dto.ScheduleDTO;
import com.r2s.findInternship.data.entity.Schedule;
import com.r2s.findInternship.data.mapper.ScheduleMapper;
import com.r2s.findInternship.data.repository.ScheduleRepository;
import com.r2s.findInternship.exception.InternalServerErrorException;
import com.r2s.findInternship.exception.ResourceNotFoundException;
import com.r2s.findInternship.service.JobTypeService;

@Service
public class JobTypeServiceImpl implements JobTypeService {

	@Autowired
	private ScheduleRepository jobTypeRepository;
	@Autowired
	private ScheduleMapper jobTypeMapper;
    @Override
    public boolean deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
    @Override
    public boolean existsById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }
    @Override
    public ScheduleDTO findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    @Override
    public List<ScheduleDTO> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    @Override
    public ScheduleDTO update(int id, ScheduleDTO jobTypeDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    @Override
    public Schedule getById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }
    @Override
    public ScheduleDTO create(ScheduleDTO jobTypeDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }
    @Override
    public boolean existsByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsByName'");
    }

	// @Override
	// public boolean deleteById(Integer id) {
	// 	jobTypeRepository.deleteById(id);
	// 	return true;
	// }

	// @Override
	// public boolean existsById(Integer id) {
	// 	return jobTypeRepository.existsById(id);
	// }

	// @Override
	// public ScheduleDTO findById(Integer id) {
	// 	return this.jobTypeMapper.toDTO(this.getById(id));
	// }

	// @Override
	// public List<ScheduleDTO> findAll() {
	// 	return jobTypeRepository.findAll().stream()
	// 			.map(JobType -> this.jobTypeMapper.toDTO(JobType)).collect(Collectors.toList());
	// }

	// @Override
	// public ScheduleDTO update(int id, ScheduleDTO jobTypeDTO) {
	// 	if (jobTypeRepository.existsByName(jobTypeDTO.getName())) {
	// 		throw new InternalServerErrorException(
	// 				String.format("Job type", "name", jobTypeDTO.getName()));
	// 	}
	// 	jobTypeRepository.save(jobTypeMapper.toEntity(jobTypeDTO));
	// 	return jobTypeDTO;
	// }

	// @Override
	// public Schedule getById(Integer id) {
	// 	return jobTypeRepository.findById(id)
	// 			.orElseThrow(() -> new ResourceNotFoundException("Job type", "id", String.valueOf(id)));
	// }

	// @Override
	// public ScheduleDTO create(ScheduleDTO jobTypeDTO) {
	// 	return jobTypeMapper.toDTO(jobTypeRepository.save(jobTypeMapper.toEntity(jobTypeDTO)));
	// }

	// @Override
	// public boolean existsByName(String name) {
	// 	return jobTypeRepository.existsByName(name);
	// }

}

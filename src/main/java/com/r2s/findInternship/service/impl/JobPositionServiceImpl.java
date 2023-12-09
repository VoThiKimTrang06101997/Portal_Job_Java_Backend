package com.r2s.findInternship.service.impl;

import java.util.List;

import com.r2s.findInternship.data.mapper.JobPositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2s.findInternship.data.dto.PositionDTO;
import com.r2s.findInternship.data.repository.JobPositionRepository;
import com.r2s.findInternship.service.JobPositionService;

@Service
public class JobPositionServiceImpl implements JobPositionService {
	@Autowired
	private JobPositionRepository jobPositionRepository;
	@Autowired
	private JobPositionMapper jobPositionMapper;
    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
    @Override
    public long count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }
    @Override
    public boolean existsById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }
    @Override
    public PositionDTO findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    @Override
    public List<PositionDTO> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    @Override
    public PositionDTO create(PositionDTO jobPositionDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }
    @Override
    public boolean existsByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsByName'");
    }

	// @Override
	// public PositionDTO create(PositionDTO jobPositionDTO) {
	// 	if (this.existsByName(jobPositionDTO.getName()))
	// 		throw new InternalServerErrorException(
	// 				String.format("Exists job position named %s", jobPositionDTO.getName()));
	// 	Position entity = jobPositionRepository.save(jobPositionMapper.toEntity(jobPositionDTO));
	// 	return this.jobPositionMapper.toDTO(jobPositionRepository.save(entity));
	// }

	// @Override
	// public List<PositionDTO> findAll() {
	// 	return jobPositionRepository.findAll().stream()
	// 			.map(JobPosition -> this.jobPositionMapper.toDTO(JobPosition)).collect(Collectors.toList());
	// }

	// @Override
	// public PositionDTO findById(Integer id) {
	// 	return this.jobPositionMapper.toDTO(this.getById(id));
	// }

	// @Override
	// public boolean existsById(Integer id) {
	// 	return jobPositionRepository.existsById(id);
	// }

	// @Override
	// public long count() {
	// 	return jobPositionRepository.count();
	// }

	// @Override
	// public boolean existsByName(String name) {
	// 	return jobPositionRepository.existsByName(name);
	// }

	// @Override
	// public void deleteById(Integer id) {
	// 	jobPositionRepository.deleteById(id);
	// }

	// public Position getById(Integer id) {
	// 	return jobPositionRepository.findById(id)
	// 			.orElseThrow(() -> new ResourceNotFoundException("Job position","id",String.valueOf(id)));

	// }

}

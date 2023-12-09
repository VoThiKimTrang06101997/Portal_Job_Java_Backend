package com.r2s.findInternship.service;

import java.util.List;

import com.r2s.findInternship.data.dto.PositionDTO;

public interface JobPositionService {

	void deleteById(Integer id);

	long count();

	boolean existsById(Integer id);

	PositionDTO findById(Integer id);

	List<PositionDTO> findAll();

	PositionDTO create(PositionDTO jobPositionDTO);

	boolean existsByName(String name);

}

package com.r2s.findInternship.service;

import java.util.List;

import com.r2s.findInternship.data.dto.UniversityTypeDTO;
import com.r2s.findInternship.data.entity.UniversityType;

public interface UniversityTypeService {

	boolean existsById(Integer id);

	UniversityTypeDTO findById(Integer id);

	List<UniversityTypeDTO> findAll();

	UniversityTypeDTO create(UniversityType universityType);

}

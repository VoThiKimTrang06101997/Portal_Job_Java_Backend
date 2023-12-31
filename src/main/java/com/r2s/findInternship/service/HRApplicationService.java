package com.r2s.findInternship.service;

import java.util.List;

import com.r2s.findInternship.common.MessageResponse;
import com.r2s.findInternship.data.dto.HRApplicationDTO;
import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.entity.HRApplication;

public interface HRApplicationService {

	HRApplicationDTO findById(Integer id);

	List<HRApplicationDTO> findAll();

	PaginationDTO findAll(int no, int limit);

	PaginationDTO findAllActive(int no, int limit);

	HRApplicationDTO create(HRApplicationDTO hrApplicationDTO);

	HRApplicationDTO update(int id, HRApplicationDTO hrApplicationDTO);

	HRApplication getById(Integer id);

	boolean deleteById(Integer id);

	MessageResponse deleteById(int id);

}

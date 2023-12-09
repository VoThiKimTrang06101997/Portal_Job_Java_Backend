package com.r2s.findInternship.service;

import com.r2s.findInternship.common.MessageResponse;
import com.r2s.findInternship.data.dto.MajorDTO;
import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.entity.Major;

import java.util.List;

public interface MajorService {

	MajorDTO findById(Integer id);

	List<MajorDTO> findAll();

	MessageResponse create(MajorDTO majorDTO);

//	boolean existsByName(String name);

	MessageResponse deleteById(Integer id);

}

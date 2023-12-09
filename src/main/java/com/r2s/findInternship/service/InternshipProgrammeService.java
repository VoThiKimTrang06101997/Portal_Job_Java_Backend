package com.r2s.findInternship.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.data.dto.InternshipProgrammeFilterDTO;
import com.r2s.findInternship.data.dto.InternshipProgrammeDTO;
import com.r2s.findInternship.data.dto.show.UniversityDemandDTOShow;
import com.r2s.findInternship.data.dto.PaginationDTO;

public interface InternshipProgrammeService {

	PaginationDTO filter(InternshipProgrammeFilterDTO demandSearchDTO, int no, int limit);

	PaginationDTO findAllByNameLike(String name, int no, int limit);

	PaginationDTO findAllActiveByUniversityId(int universityId, int no, int limit);

	UniversityDemandDTOShow findById(int id);

	InternshipProgrammeDTO readJson(String content, MultipartFile file);

	List<UniversityDemandDTOShow> findAll();

	UniversityDemandDTOShow create(InternshipProgrammeDTO universityDemandDTO);

	UniversityDemandDTOShow update(InternshipProgrammeDTO universityDemandDTO, int id);

	void deleteById(int id);

	UniversityDemandDTOShow updateStatus(InternshipProgrammeDTO universityDemandDTO);

    PaginationDTO findAllActive(int no, int limit);

}

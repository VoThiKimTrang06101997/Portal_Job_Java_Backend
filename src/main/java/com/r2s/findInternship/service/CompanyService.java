package com.r2s.findInternship.service;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.data.dto.CompanyDTO;
import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.entity.Company;

public interface CompanyService {

	PaginationDTO findAllByNameLike(String name, int no, int limit);
	//
	PaginationDTO findAll(int no, int limit);

	PaginationDTO findAllActive(int no, int limit);

	CompanyDTO findByJobId(long jobId);

	@Transactional
	CompanyDTO create(CompanyDTO companyDTO, MultipartFile fileLogo);

	@Transactional
	CompanyDTO update(long id, CompanyDTO companyDTO, MultipartFile fileLogo);

	// Find by ID ---> Get by ID
	CompanyDTO findById(long id);

	Company getById(long id);

	boolean deleteById(long id);

	// Map<String, String> checkCompany(int id, CompanyDTO companyDTO);
	//
	// Map<String, String> checkCompany(CompanyDTO companyDTO);

	// CompanyDTO readJson(String value, MultipartFile fileLogo);

	// void flush();

	Long countByCreatedDate(Date from, Date to);

	// List<Object[]> getStatusStatistics();

	// List<Object[]> getNewStatistics();

}

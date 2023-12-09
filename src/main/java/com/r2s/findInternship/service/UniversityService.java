package com.r2s.findInternship.service;

import java.time.LocalDateTime;
import java.util.List;

import com.r2s.findInternship.common.MessageResponse;
import org.springframework.data.domain.Sort;

import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.dto.UniversityDTO;
import com.r2s.findInternship.data.entity.University;

public interface UniversityService {

    long count();

    boolean existsById(Integer id);

    UniversityDTO findById(Integer id);

    List<University> findAll(Sort sort);

    List<UniversityDTO> findAllByNameLike(String name);

    List<UniversityDTO> findAllByShortNameLike(String shortName);

    List<UniversityDTO> findAll();

    PaginationDTO findAll(int no, int limit);

    PaginationDTO findAllByNameLike(String name, int no, int limit);

    PaginationDTO findAllByShortNameLike(String shortName, int no, int limit);

    University getById(Integer id);

    List<UniversityDTO> findAllSort(String field);

    MessageResponse deleteById(int id);

    MessageResponse create(UniversityDTO universityDTO);

    void recoverById(int id);

    List<Object[]> getNewStatistics();

    Long countByCreatedDate(LocalDateTime from, LocalDateTime to);

    List<Object[]> getStatusStatistics();

    PaginationDTO findAllByProvinceId(int provinceId, int no, int limit);

    UniversityDTO changeStatus(int id, UniversityDTO universityDTO);
}

package com.r2s.findInternship.service;

import java.time.LocalDateTime;
import java.util.List;

import com.r2s.findInternship.common.MessageResponse;
import com.r2s.findInternship.data.dto.*;
import com.r2s.findInternship.data.dto.show.JobDTOShow;

public interface JobService {

    PaginationDTO findAllActive(int no, int limit);
//
//        PaginationDTO findAllActiveByNameLikeAndProvinceName(String name, String provinceName, int no, int limit);
//
//        PaginationDTO findAllActiveByHrId(int hrId, int no, int limit);
//
//        PaginationDTO findAll(int no, int limit);
//
//        PaginationDTO findAllActiveByCompanyIdShowForHr(int companyId, int no, int limit);
//
//        PaginationDTO findAllDisableByCompanyIdShowForHr(int companyId, int no, int limit);
//
//        PaginationDTO findAllByCompanyIdShowForHr(int companyId, int no, int limit);
//
//        long countAllActiveByCompanyIdShowForHr(int companyId);
//
//        long countAllDisableByCompanyIdShowForHr(int companyId);
//
//        long countAllByCompanyId(int companyId);
//
//        PaginationDTO findAllActiveByUserId(long userId, int no, int limit);
//
//        PaginationDTO findAllActiveByUsername(String username, int no, int limit);
//
//        PaginationDTO findAllByUserId(long userId, int no, int limit);
//
//        PaginationDTO findAllDisableByUserId(long userId, int no, int limit);
//
//        PaginationDTO findAllActiveByDistrictNameAndProvinceName(String districtName, String provinceName, int no,
//                        int limit);
//
//        PaginationDTO findAllActiveByNameLikeAndCaredCandidateId(String name, int caredCandidateId, int no, int limit);
//
//        PaginationDTO findAllActiveByNameLikeAndAppliedCandidateId(String name, int appliedCandidateId, int no,
//                        int limit);
//
//        PaginationDTO filterByKeywords(String name, Integer provinceId, List<String> jobTypeNames,
//                        List<String> jobPositionNames, List<String> majorNames, String order, int no, int limit);
//
//        MessageResponse deleteById(Integer id);
//
//        long count();
//
//        boolean existsById(Integer id);

    JobDTO findById(long id);
//
//        JobDTOShow findByIdToShow(Integer id);
//
//        List<JobDTO> findAllActive();
//
//        List<JobDTO> findAll();

    JobDTO create(JobCreationDTO jobCreationDTO);

    JobDTO  update(long id, JobDTO jobDTO);

    JobDTO replicate(long id, JobDTO jobDTO);

    // JobDTO changeStatus(int id, JobDTO jobDTO);
//
    Long countByCreatedDate(LocalDateTime from, LocalDateTime to);
//
//        List<JobDTO> findAllActiveByCompanyId(Integer companyId);
//
//        List<JobDTO> findAllByUserId(long userId);

    //   List<JobDTO> findAllByUsername(String username);
//
//        List<Object[]> getStatusStatistics();
//
    List<Object[]> getNewStatistics();
//
//        List<Object[]> getMajorStatistics();

//        List<Object[]> getPositionStatistics();

//        PaginationDTO filterAllPostedJobOfCompanyShowForHr(int companyId,
//                PostedJobOfCompanyFilterByHrDTO jobOfCompanyFilterByHrDTO, int no, int limit);


    boolean isAppliable(JobDTO jobDTO);

    PaginationDTO filterJob(JobFilterDTO jobFilterDTO, int no, int limit);
}

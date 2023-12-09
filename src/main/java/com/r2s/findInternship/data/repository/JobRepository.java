package com.r2s.findInternship.data.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.data.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {

     Page<Job> findAll(Specification<Job> filter, Pageable pageable);

//     Job findById(long id);
    @Query("SELECT j FROM Job j WHERE j.status.id = 1")
    List<Job> findJobActive();

//     @Query("SELECT j FROM Job j WHERE j.status.id = 1 AND j.name LIKE %:jobName% ORDER BY j.createdDate DESC")
//     Page<Job> findAllActiveByNameLike(@Param("jobName") String jobName, Pageable pageable);

//     @Query(value = "SELECT j FROM Job j WHERE"
//             + " j.status.id = 1"
//             + " AND j.name LIKE %:jobName%"
//             + " AND j.location.district.province.name = :provinceName ORDER BY j.createdDate DESC")
//     Page<Job> findAllActiveByNameLikeAndProvinceName(@Param("jobName") String jobName,
//             @Param("provinceName") String provinceName,
//             Pageable pageable);

     @Query(value = "SELECT j FROM Job j WHERE j.status.id = 1 ORDER BY j.createdDate DESC")
     Page<Job> findAllActive(Pageable pageable);

//     @Query(value = "SELECT j FROM Job j"
//             + " WHERE j.status.id = 1"
//             + " AND j.location.district.name = :districtName"
//             + " AND j.location.district.province.name = :provinceName ORDER BY j.createdDate DESC")
//     Page<Job> findAllActiveByDistrictNameAndProvinceName(@Param("districtName") String districtName,
//             @Param("provinceName") String provinceName, Pageable pageable);

//     @Query("SELECT j FROM Job j WHERE j.status.id = 1 AND j.hr.id = :hrId ORDER BY j.createdDate DESC")
//     Page<Job> findAllActiveByHrId(@Param("hrId") int hrId, Pageable pageable);

//     @Query(value = "SELECT j FROM Job j"
//             + " WHERE j.status.id = 1"
//             + " AND j.name LIKE %:jobName%"
//             + " AND j.id IN (SELECT jc.job.id FROM JobCare jc WHERE jc.candidate.id = :candidateId)")
//     Page<Job> findAllActiveByNameLikeAndCaredCandidateId(@Param("jobName") String jobName,
//             @Param("candidateId") int candidateId, Pageable pageable);

//     @Query(value = "SELECT j FROM Job j"
//             + " WHERE j.status.id = 1"
//             + " AND j.name LIKE %:jobName%"
//             + " AND j.id IN (SELECT ca.job.id FROM CandidateApplication ca WHERE ca.candidate.id = :candidateId)")
//     Page<Job> findAllActiveByNameLikeAndAppliedCandidateId(@Param("jobName") String jobName,
//             @Param("candidateId") int candidateId, Pageable pageable);

//     @Query("SELECT j FROM Job j WHERE j.status.id = 1 AND j.hr.company.id = :companyId ORDER BY j.createdDate DESC")
//     Page<Job> findAllActiveByCompanyId(@Param("companyId") int companyId, Pageable pageable);

//     @Query("SELECT j FROM Job j WHERE j.status.id = 1 AND j.hr.company.id = :companyId ORDER BY j.createdDate DESC")
//     List<Job> findAllActiveByCompanyId(@Param("companyId") int companyId);

//     @Query("SELECT j FROM Job j WHERE j.status.id = 4 AND j.hr.company.id = :companyId ORDER BY j.createdDate DESC")
//     Page<Job> findAllDisableByCompanyId(@Param("companyId") int companyId, Pageable pageable);

//     @Query("SELECT j FROM Job j WHERE j.status.id = 4 AND j.hr.company.id = :companyId ORDER BY j.createdDate DESC")
//     List<Job> findAllDisableByCompanyId(@Param("companyId") int companyId);

//     @Query("SELECT j FROM Job j WHERE j.hr.company.id = :companyId ORDER BY j.createdDate DESC")
//     Page<Job> findAllByCompanyId(@Param("companyId") int companyId, Pageable pageable);

//     @Query("SELECT j FROM Job j WHERE j.hr.company.id = :companyId ORDER BY j.createdDate DESC")
//     List<Job> findAllByCompanyId(@Param("companyId") int companyId);

//     @Query("SELECT COUNT(j.id) FROM Job j WHERE j.status.id = 1 AND j.hr.company.id = :companyId")
//     long countAllActiveByCompanyId(@Param("companyId") int companyId);

//     @Query("SELECT COUNT(j.id) FROM Job j WHERE j.status.id = 4 AND j.hr.company.id = :companyId")
//     long countAllDisableByCompanyId(@Param("companyId") int companyId);

//     @Query("SELECT COUNT(j.id) FROM Job j WHERE j.hr.company.id = :companyId")
//     long countAllByCompanyId(@Param("companyId") int companyId);

//     @Query("SELECT j FROM Job j WHERE j.status.id = 1 AND j.hr.user.id = :userId ORDER BY j.createdDate DESC")
//     Page<Job> findAllActiveByUserId(@Param("userId") long userId, Pageable pageable);

//     @Query("SELECT j FROM Job j WHERE j.status.id = 4 AND j.hr.user.id = :userId ORDER BY j.createdDate DESC")
//     Page<Job> findAllDisableByUserId(@Param("userId") long userId, Pageable pageable);

//     @Query("SELECT j FROM Job j WHERE j.hr.user.id = :userId ORDER BY j.createdDate DESC")
//     Page<Job> findAllByUserId(@Param("userId") long userId, Pageable pageable);

//     @Query("SELECT j FROM Job j WHERE j.status.id = 1 AND j.hr.user.username = :username ORDER BY j.createdDate DESC")
//     Page<Job> findAllActiveByUsername(@Param("username") String username, Pageable pageable);

//     @Query("SELECT j FROM Job j WHERE j.status.id = 1 ORDER BY j.createdDate DESC")
//     List<Job> findAllActive();

     Long countByCreatedDateBetween(LocalDateTime from, LocalDateTime to);

     @Query("SELECT j.id, j.name, j.createdDate FROM Job j WHERE j.createdDate >= :timeAgo")
     List<Object[]> getNewStatistics(@Param("timeAgo") LocalDateTime timeAgo);

     @Query("SELECT j.status.name, COUNT(j.id) FROM Job j GROUP BY j.status.name")
     List<Object[]> getStatusStatistics();

//     @Query("SELECT j FROM Job j WHERE j.hr.id =:hrId ORDER BY j.createdDate ASC")
//     List<Job> findAllByHRId(@Param("hrId") int hrId);

//     @Query("SELECT j FROM Job j WHERE j.hr.user.id = :userId ORDER BY j.createdDate ASC")
//     List<Job> findAllByUserId(@Param("userId") long userId);

//     @Query("SELECT j FROM Job j WHERE j.hr.user.username = :username ORDER BY j.createdDate ASC")
//     List<Job> findAllByUsername(@Param("username") String username);

//     @Query("SELECT j.jobPosition.name, COUNT(j.id) FROM Job j GROUP BY j.jobPosition.name")
//     List<Object[]> getPositionStatistics();

//     @Query("SELECT j.major.name, COUNT(j.id) FROM Job j GROUP BY j.major.name")
//     List<Object[]> getMajorStatistics();

//     @Query("SELECT j FROM Job j"
//             + " WHERE j.hr.company.id = :companyId"
//             + " AND (:quickSearch IS NULL"
//             + "     OR (j.name LIKE %:quickSearch%"
//             + "         OR CONCAT(j.hr.user.lastName, ' ', j.hr.user.firstName) LIKE %:quickSearch%))" // full name like
//                                                                                                        // quickSearch
//             + " AND (:provinceName IS NULL OR j.location.district.province.name = :provinceName)"
//             + " AND (:endDate IS NULL OR DATE(j.endDate) = DATE(:endDate))"
//             + " AND (:statusId IS NULL OR j.status.id = :statusId)")
//     Page<Job> filterPostedJobOfCompanyByHr(
//             @Param("companyId") int companyId,
//             @Param("quickSearch") String quickSearch,
//             @Param("provinceName") String provinceName,
//             @Param("endDate") LocalDateTime endDate,
//             @Param("statusId") Integer statusId,
//             Pageable pageable);

}

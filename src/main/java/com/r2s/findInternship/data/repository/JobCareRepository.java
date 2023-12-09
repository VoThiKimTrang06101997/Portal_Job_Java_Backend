package com.r2s.findInternship.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.data.entity.JobCare;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobCareRepository extends JpaRepository<JobCare, Long> {

     @Query("SELECT CASE WHEN COUNT(jc.id) > 0 THEN TRUE ELSE FALSE END FROM JobCare jc"
             + " WHERE jc.candidate.id = :candidateId AND jc.job.id = :jobId")
     boolean existsByCandidateIdAndJobId(@Param("candidateId") long candidateId, @Param("jobId") long jobId);

     @Query("SELECT jc FROM JobCare jc WHERE jc.candidate.id = :candidateId AND jc.job.id = :jobId")
     Optional<JobCare> findByCandidateIdAndJobId(@Param("candidateId") long candidateId, @Param("jobId") long jobId);

     @Query("SELECT jc FROM JobCare jc WHERE jc.candidate.id = :candidateId")
     Page<JobCare> findAllByCandidateId(@Param("candidateId") long candidateId, Pageable pageable);

     @Query("SELECT jc.job.id FROM JobCare jc WHERE jc.candidate.id = :candidateId GROUP BY jc.job.id")
     List<Integer> finJobSave(@Param("candidateId") long candidateId);


    // @Query("SELECT jc FROM JobCare jc WHERE jc.job.id = :jobId")
    // Page<JobCare> findAllByJobId(@Param("jobId") int jobId, Pageable pageable);


}

package com.r2s.findInternship.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.data.entity.JobPosition;

import java.util.List;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPosition, Long> {

    List<JobPosition> findAllByJob_Id(long jobId);

}

package com.r2s.findInternship.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.data.entity.JobMajor;

import java.util.List;

@Repository
public interface JobMajorRepository extends JpaRepository<JobMajor, Long> {

    List<JobMajor> findAllByJob_Id(long jobId);

}

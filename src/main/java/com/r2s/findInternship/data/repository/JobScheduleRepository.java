package com.r2s.findInternship.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.data.entity.JobSchedule;

import java.util.List;

@Repository
public interface JobScheduleRepository extends JpaRepository<JobSchedule, Long> {

    List<JobSchedule> findAllByJob_Id(long jobId);

}

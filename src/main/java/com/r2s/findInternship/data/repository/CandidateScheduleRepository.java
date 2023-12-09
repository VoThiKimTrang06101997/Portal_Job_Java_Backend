package com.r2s.findInternship.data.repository;

import com.r2s.findInternship.data.entity.CandidateSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateScheduleRepository extends JpaRepository<CandidateSchedule, Long> {
    List<CandidateSchedule> findAllByCandidate_Id(long candidateId);
}

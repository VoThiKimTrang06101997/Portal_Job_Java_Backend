package com.r2s.findInternship.data.repository;

import com.r2s.findInternship.data.entity.CandidatePosition;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatePositionRepository extends JpaRepository<CandidatePosition, Long> {

    List<CandidatePosition> findAllByCandidate_Id(long candidateId);

}

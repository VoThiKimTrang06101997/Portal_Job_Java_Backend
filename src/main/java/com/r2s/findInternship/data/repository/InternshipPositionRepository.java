package com.r2s.findInternship.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.data.entity.InternshipPosition;

@Repository
public interface InternshipPositionRepository extends JpaRepository<InternshipPosition, Long> {

}

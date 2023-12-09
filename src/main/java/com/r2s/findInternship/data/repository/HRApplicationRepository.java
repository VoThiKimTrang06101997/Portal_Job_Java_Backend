package com.r2s.findInternship.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.data.entity.HRApplication;

@Repository
public interface HRApplicationRepository extends JpaRepository<HRApplication, Long> {

    // @Query("SELECT ha FROM HRApplication ha WHERE ha.status.id = 1")
    // Page<HRApplication> findAllActive(Pageable pageable);
}

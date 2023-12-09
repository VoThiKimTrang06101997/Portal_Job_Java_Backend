package com.r2s.findInternship.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.data.entity.CompanyRate;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRateRepository extends JpaRepository<CompanyRate, Long> {

    // @Query("SELECT r FROM Rate r WHERE r.status.id = 1")
    // List<CompanyRate> findAllActive();

    // @Query("SELECT r FROM Rate r WHERE r.status.id = 1 AND r.company.id = :companyId")
    // Page<CompanyRate> findAllActiveByCompanyId(@Param("companyId") int companyId, Pageable pageable);

    // @Query("SELECT r FROM Rate r WHERE r.user.username = :username AND r.company.id = :companyId")
    // Optional<CompanyRate> findByUsernameAndCompanyId(@Param("username") String username, @Param("companyId") int companyId);

    // @Query("SELECT r FROM Rate r WHERE r.status.id = 1 AND r.user.username = :username AND r.company.id = :companyId")
    // Optional<CompanyRate> findActiveByUsernameAndCompanyId(@Param("username") String username, @Param("companyId") int companyId);

    // @Query("SELECT r FROM Rate r WHERE r.company.id = :companyId")
    // Page<CompanyRate> findAllByCompanyId(@Param("companyId") int companyId, Pageable pageable);

}

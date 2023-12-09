package com.r2s.findInternship.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.data.entity.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

	// @Query("SELECT p FROM Partner p WHERE p.user.id = :userId")
	// Optional<Partner> findByUserId(@Param("userId") long userId);

	// @Query("SELECT p FROM Partner p WHERE p.university.id = :universityId")
	// List<Partner> findAllByUniversityId(@Param("universityId") int universityId);

	// @Query("SELECT p FROM Partner p WHERE p.university.id = :universityId")
	// Page<Partner> findAllByUniversityId(@Param("universityId") int universityId, Pageable pageable);

}

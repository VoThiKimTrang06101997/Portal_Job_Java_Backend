package com.r2s.findInternship.data.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.data.entity.InternshipProgramme;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface InternshipProgrammeRepository extends JpaRepository<InternshipProgramme, Long>,
		JpaSpecificationExecutor<InternshipProgramme> {

	 @Query("SELECT ip FROM InternshipProgramme ip WHERE ip.status.id = 1 ORDER BY ip.createdDate DESC")
	 Page<InternshipProgramme> findAllActive(Pageable pageable);

	 Page<InternshipProgramme> findAllByTitleLike(String name, Pageable pageable);

	 @Query("SELECT ip FROM InternshipProgramme ip"
	 		+ " WHERE ip.status.id = 1 AND ip.university.id = :universityId ORDER BY createdDate DESC")
	 Page<InternshipProgramme> findAllActiveByUniversityId(@Param("universityId") int universityId, Pageable pageable);

	// Page<InternshipProgramme> findAll(Specification<InternshipProgramme> filter, Pageable pageable);

}

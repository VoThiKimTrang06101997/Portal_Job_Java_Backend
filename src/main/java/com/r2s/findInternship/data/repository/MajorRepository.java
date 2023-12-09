package com.r2s.findInternship.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.data.entity.Major;

@Repository
public interface MajorRepository extends JpaRepository<Major, Integer> {

    Major findById(int id);

    boolean existsByName(String name);

	// @Query("SELECT m FROM Major m"
	// 		+ " WHERE m.id IN (SELECT md.id FROM MajorDemand md WHERE md.universityDemand.id = :universityDemandId)")
	// List<Major> findAllByUniversityDemandId(@Param("universityDemandId") int universityDemandId);
}

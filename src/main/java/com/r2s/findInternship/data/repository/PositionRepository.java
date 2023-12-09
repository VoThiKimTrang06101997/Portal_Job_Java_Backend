package com.r2s.findInternship.data.repository;

import com.r2s.findInternship.data.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {

	 boolean existsByName(String name);

	// @Query("SELECT jp FROM JobPosition jp"
	// 		+ " WHERE jp.id IN (SELECT jpd FROM JobPositionDemand jpd WHERE jpd.universityDemand.id = :universityDemandId)")
	// List<Position> findAllByUniversityDemandId(@Param("universityDemandId") int universityDemandId);

    Position findById(int id);

}

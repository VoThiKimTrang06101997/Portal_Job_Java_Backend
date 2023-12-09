package com.r2s.findInternship.data.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.r2s.findInternship.data.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
	
	boolean existsByName(String name);
	
	Optional<Status> findByName(String name);
}

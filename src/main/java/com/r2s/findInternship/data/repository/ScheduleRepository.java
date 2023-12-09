package com.r2s.findInternship.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.data.entity.Schedule;
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    Schedule findById(int id);
	// String findNameById(int id);

	// Schedule findByName(String name);

	 boolean existsByName(String name);

}

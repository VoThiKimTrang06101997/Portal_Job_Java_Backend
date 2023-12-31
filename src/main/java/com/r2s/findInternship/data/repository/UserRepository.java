package com.r2s.findInternship.data.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.r2s.findInternship.data.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);

	Page<User> findALLByEmailLike(String username, Pageable pageable);

	boolean existsByIdNotAndEmail(long id, String email);

	Optional<User> findByPasswordForgotToken(String token);

	User findByTokenActive(String token);

	@Query("SELECT COUNT(u.id) from User u"
			+ " WHERE (:from IS NULL OR :from <= u.createdDate)"
			+ " AND (:to IS NULL OR u.createdDate <= :to)")
	Long countByCreatedDateBetween(Date from, Date to);


}

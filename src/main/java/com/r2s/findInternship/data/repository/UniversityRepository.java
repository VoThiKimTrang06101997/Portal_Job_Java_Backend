package com.r2s.findInternship.data.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.data.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

        // Page<University> findAllByShortNameLike(String shortName, Pageable pageable);

        // Page<University> findAllByNameLike(String name, Pageable pageable);

        // @Query("SELECT DISTINCT u FROM University u"
        //                 + " WHERE u.id IN (SELECT ul.university.id FROM UniversityLocation ul"
        //                 + " WHERE ul.location.district.province.id = :provinceId)")
        // Page<University> findAllByProvinceId(@Param("provinceId") int provinceId, Pageable pageable);

        // @Query("SELECT DISTINCT u FROM University u"
        //                 + " WHERE u.id IN (SELECT ul.university.id FROM UniversityLocation ul"
        //                 + " WHERE ul.location.district.province.id = :provinceId)")
        // List<University> findAllByProvinceId(@Param("provinceId") int provinceId);

        // Optional<University> findByName(String name);

        // Optional<University> findByShortName(String shortName);

        // List<University> findAllByNameLike(String name);

        // List<University> findAllByShortNameLike(String shortName);

        // boolean existsByEmail(String email);

        // Optional<University> findByEmail(String email);

        // Optional<University> findByWebsite(String website);

        // boolean existsByWebsite(String website);

        // boolean existsByShortName(String website);

         boolean existsByName(String name);

        // Long countByCreatedDateBetween(LocalDateTime from, LocalDateTime to);

        // @Query("SELECT u.name, u.shortName, u.createdDate FROM University u WHERE u.createdDate >= :timeAgo")
        // List<Object[]> getNewStatistics(@Param("timeAgo") LocalDateTime timeAgo);

        // @Query("SELECT u.status.name, COUNT(u.id) FROM University u GROUP BY u.status.name")
        // List<Object[]> getStatusStatistics();

}

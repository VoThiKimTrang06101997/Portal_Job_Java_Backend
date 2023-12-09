package com.r2s.findInternship.data.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.r2s.findInternship.data.dto.RequestCandidateDTO;
import com.r2s.findInternship.data.entity.Candidate;
import com.r2s.findInternship.data.entity.CandidatePosition;
import com.r2s.findInternship.data.entity.CandidateSchedule;
import com.r2s.findInternship.data.entity.CandidateMajor;

public class CandidateSpecification implements Specification<Candidate> {

	@Override
	public Predicate toPredicate(Root<Candidate> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Specification<Candidate> filter(RequestCandidateDTO requestCandidateDTO) {
		return ((root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			query.distinct(true);

			Join<Candidate, CandidateMajor> majorCandidateJoin = root.join("majorCandidates", JoinType.LEFT);
			Join<Candidate, CandidatePosition> jobPositionCandidateJoin = root.join("jobPositionCandidates",
					JoinType.LEFT);
			Join<Candidate, CandidateSchedule> jobTypeCandidateJoin = root.join("jobTypeCandidates", JoinType.LEFT);

			if (requestCandidateDTO.getDesiredWorkingProvinceId() != null
					&& requestCandidateDTO.getDesiredWorkingProvinceId() > 0) {
				Integer desiredWorkingProvinceId = requestCandidateDTO.getDesiredWorkingProvinceId();
				predicates.add(criteriaBuilder.equal(root.get("desiredWorkingProvince"), desiredWorkingProvinceId));
			}

			if (requestCandidateDTO.getDesiredJob() != null && !requestCandidateDTO.getDesiredJob().trim().isEmpty()) {
				String[] arrDesiredJobs = requestCandidateDTO.getDesiredJob().trim().split(" ");
				Predicate predicateDesiredJob = null;
				for (String arrDesiredJob : arrDesiredJobs) {
					if (predicateDesiredJob == null) {
						predicateDesiredJob = criteriaBuilder
								.or(criteriaBuilder.like(criteriaBuilder.upper(root.get("desiredJob")),
										"%" + arrDesiredJobs[0].toUpperCase() + "%"));
					} else {

						predicateDesiredJob = criteriaBuilder.or(predicateDesiredJob,
								criteriaBuilder.like(criteriaBuilder.upper(root.get("desiredJob")),
										"%" + arrDesiredJob.toUpperCase() + "%"));
					}
				}

				predicates.add(predicateDesiredJob);

			}

			predicates.add(criteriaBuilder.isTrue(root.get("jobStatus")));

			if (requestCandidateDTO.getMajorIds() != null && requestCandidateDTO.getMajorIds().length > 0) {
				Integer[] majorIds = requestCandidateDTO.getMajorIds();

				predicates.add(getQueryMultipleValues("major", majorIds, criteriaBuilder, majorCandidateJoin));

			}
			if (requestCandidateDTO.getJobTypeIds() != null && requestCandidateDTO.getJobTypeIds().length > 0) {
				Integer[] jobTypeids = requestCandidateDTO.getJobTypeIds();
				predicates.add(getQueryMultipleValues("jobType", jobTypeids, criteriaBuilder, jobTypeCandidateJoin));

			}
			if (requestCandidateDTO.getJobPositionIds() != null && requestCandidateDTO.getJobPositionIds().length > 0) {
				Integer[] jobPositionIds = requestCandidateDTO.getJobPositionIds();
				predicates.add(getQueryMultipleValues("jobPosition", jobPositionIds, criteriaBuilder,
						jobPositionCandidateJoin));

			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		});

	}

	private static Predicate getQueryMultipleValues(String attribute, Integer[] values, CriteriaBuilder criteriaBuilder,
			Join<?, ?> join) {
		javax.persistence.criteria.Predicate nameConditions = null;
		for (Integer value : values) {

			if (nameConditions == null) {
				nameConditions = criteriaBuilder.or(criteriaBuilder.equal(join.get(attribute), value));
			} else {
				nameConditions = criteriaBuilder.or(nameConditions,
						criteriaBuilder.equal(join.get(attribute), value));
			}
		}

		return (Predicate) nameConditions;
	}

}

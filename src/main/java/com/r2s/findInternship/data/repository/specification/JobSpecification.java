package com.r2s.findInternship.data.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.r2s.findInternship.data.dto.JobFilterDTO;
import com.r2s.findInternship.data.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

public class JobSpecification implements Specification<Job> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        // TODO Auto-generated method stub
        return null;
    }

    //String name, Integer proviceId, List<String> jobPositionNames,
//
//			List<String> jobTypeNames, List<String> majorNames, String order
//    public static Specification<Job> filterJobForCandidate(JobFilterDTO jobFilterDTO) {
//        return ((root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            //Join<Province, Job> provinceJoin = root.join("location").join("district").join("province", JoinType.LEFT);
//            Join<Position, Job> jobPositionJoin = root.join("jobPosition");
//            Join<Schedule, Job> jobTypeJoin = root.join("jobSchedule", JoinType.LEFT);
//            Join<Major, Job> majorJoin = root.join("major", JoinType.LEFT);
//            if (jobFilterDTO.getName() != null && !jobFilterDTO.getName().trim().isEmpty()) {
//                predicates.add(
//                        criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + jobFilterDTO.getName().toUpperCase() + "%"));
//
//            }
//            if (jobFilterDTO.getProvinceName() != null) {
//                predicates.add(criteriaBuilder.like(root.get("workLocation"), "%" + jobFilterDTO.getProvinceName().trim() + "%"));
//            }
////			if (jobFilterDTO.getProvinceId() != null ) {
////				predicates.add(criteriaBuilder.equal(provinceJoin.get("id"), jobFilterDTO.getProvinceId()));
////
////			}
//            if (jobFilterDTO.getPositionIds() != null && jobFilterDTO.getPositionIds().size() > 0) {
//                predicates.add(getQueryMultipleField(jobFilterDTO.getPositionIds(), "id", criteriaBuilder, jobPositionJoin));
//            }
//
//            if (jobFilterDTO.getScheduleIds() != null && jobFilterDTO.getScheduleIds().size() > 0) {
//                predicates.add(getQueryMultipleField(jobFilterDTO.getScheduleIds(), "id", criteriaBuilder, jobTypeJoin));
//            }
//            if (jobFilterDTO.getMajorIds() != null && jobFilterDTO.getMajorIds().size() > 0) {
//                predicates.add(getQueryMultipleField(jobFilterDTO.getMajorIds(), "name", criteriaBuilder, majorJoin));
//            }
//            predicates.add(criteriaBuilder.isTrue(root.get("status")));
//
//            query.orderBy(criteriaBuilder.desc(root.get("createdDate")));
//            if (jobFilterDTO.getOrder() != null && jobFilterDTO.getOrder().equals("oldest")) {
//                query.orderBy(criteriaBuilder.asc(root.get("createdDate")));
//            }
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//        });
//    }

    static int STATUS_ACTIVE = 1;

    public static Specification<Job> filterJobForCandidate(JobFilterDTO jobFilterDTO) {
        return (root, query, cb) -> {
            List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();

            // add name filter
            if (jobFilterDTO.getName() != null) {
                Join<Job, Company> companyJoin = root.join("company");
                predicates.add(cb.or(
                        cb.like(root.get("name"), "%" + jobFilterDTO.getName() + "%"),
                        cb.like(root.join("company").get("name"), "%" + jobFilterDTO.getName() + "%")
                ));
            }

            // add position filter
            if (jobFilterDTO.getPositionIds() != null && !jobFilterDTO.getPositionIds().isEmpty()) {
                Join<Job, JobPosition> positionJoin = root.join("jobPositions");
                predicates.add(positionJoin.get("position").get("id").in(jobFilterDTO.getPositionIds()));
                query.distinct(true);
            }

            // add schedule filter
            if (jobFilterDTO.getScheduleIds() != null && !jobFilterDTO.getScheduleIds().isEmpty()) {
                Join<Job, JobSchedule> scheduleJoin = root.join("jobSchedules");
                predicates.add(scheduleJoin.get("schedule").get("id").in(jobFilterDTO.getScheduleIds()));
                query.distinct(true);
            }

            // add major filter
            if (jobFilterDTO.getMajorIds() != null && !jobFilterDTO.getMajorIds().isEmpty()) {
                Join<Job, JobMajor> majorJoin = root.join("jobMajors");
                predicates.add(majorJoin.get("major").get("id").in(jobFilterDTO.getMajorIds()));
                query.distinct(true);
            }

            // add province filter
            if (jobFilterDTO.getProvinceName() != null) {
            String modifiedString = jobFilterDTO.getProvinceName().replaceAll("Thành phố |Tỉnh ", "");
                predicates.add(cb.like(root.get("location"), "%" + modifiedString + "%"));
            }

            //add order filter
            query.orderBy(cb.desc(root.get("createdDate")));

            //add status filter
            predicates.add(cb.equal(root.get("status"), STATUS_ACTIVE));


            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

//
//    private static Predicate getQueryMultipleField(List<String> values, String attribute,
//                                                   CriteriaBuilder criteriaBuilder, Join<?, ?> join) {
//
//			List<String> jobTypeNames, List<String> majorNames, String order) {
//		return ((root, query, criteriaBuilder) -> {
//			List<Predicate> predicates = new ArrayList<>();
////			Join<Province, Job> provinceJoin = root.join("location").join("district").join("province", JoinType.LEFT);
//			Join<Position, Job> jobPositionJoin = root.join("jobPosition");
//			Join<Schedule, Job> jobTypeJoin = root.join("jobType", JoinType.LEFT);
//			Join<Major, Job> majorJoin = root.join("major", JoinType.LEFT);
//			if (name != null && !name.trim().isEmpty()) {
//				predicates.add(
//						criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name.toUpperCase() + "%"));
//
//			}
//
//			if (proviceId != null ) {
////				predicates.add(criteriaBuilder.equal(provinceJoin.get("id"), proviceId));
//
//			}
//			if (jobPositionNames != null && jobPositionNames.size() > 0) {
//				predicates.add(getQueryMultipleField(jobPositionNames, "name", criteriaBuilder, jobPositionJoin));
//			}
//
//			if (jobTypeNames != null && jobTypeNames.size() > 0) {
//				predicates.add(getQueryMultipleField(jobTypeNames, "name", criteriaBuilder, jobTypeJoin));
//			}
//			if(majorNames!=null && majorNames.size()>0) {
//				predicates.add(getQueryMultipleField(majorNames, "name", criteriaBuilder, majorJoin));
//			}
//			predicates.add(criteriaBuilder.isTrue(root.get("status")));
//
//			query.orderBy(criteriaBuilder.desc(root.get("createdDate")));
//	        if (order!=null && order.equals("oldest")) {
//	        	query.orderBy(criteriaBuilder.asc(root.get("createdDate")));
//	        }
//
//			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//		});
//	}
//
//	private static Predicate getQueryMultipleField(List<String> values, String attribute,
//			CriteriaBuilder criteriaBuilder, Join<?, ?> join) {
//
//		Predicate nameConditions = null;
//		for (String value : values) {
//			System.out.println(value);
//			if (nameConditions == null) {
//				nameConditions = criteriaBuilder.or(criteriaBuilder.like(join.get(attribute), value));
//			} else {
//				nameConditions = criteriaBuilder.or(nameConditions,
//						criteriaBuilder.equal(join.get(attribute), value));
//			}
//		}
//		return (Predicate) nameConditions;
//	}

}

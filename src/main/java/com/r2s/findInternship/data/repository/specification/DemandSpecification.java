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

import com.r2s.findInternship.data.dto.InternshipProgrammeFilterDTO;
import com.r2s.findInternship.data.entity.InternshipMajor;
import com.r2s.findInternship.data.entity.Partner;
import com.r2s.findInternship.data.entity.University;
import com.r2s.findInternship.data.entity.InternshipProgramme;

public final class DemandSpecification implements Specification<InternshipProgramme> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static Specification<InternshipProgramme> filter(InternshipProgrammeFilterDTO demandFilterDTO) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<InternshipProgramme, InternshipMajor> MajorJoin = root.join("majorDemands", JoinType.LEFT);
            Join<Partner, InternshipProgramme> demandPartnerJoin = root.join("partner", JoinType.LEFT);
            Join<Partner, University> PartnerUniversityJoin = demandPartnerJoin.join("university",
                    JoinType.LEFT);
            query.distinct(true);
           
            if (demandFilterDTO.getName()!=null && !demandFilterDTO.getName().trim().isEmpty()) {
            	String name = demandFilterDTO.getName();
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name.toUpperCase() + "%"));
                       
            }
//   
            if (demandFilterDTO.getUniversityTypeIds()!=null && demandFilterDTO.getUniversityTypeIds().size()>0) {
            	List<String> universityTypeIds = demandFilterDTO.getUniversityTypeIds();
                predicates.add(getQueryMultipleField("universityType", universityTypeIds, criteriaBuilder, PartnerUniversityJoin));
            }
            if (demandFilterDTO.getMajorIds() != null && demandFilterDTO.getMajorIds().size() > 0) {
                List<String> majorIds = demandFilterDTO.getMajorIds();
                predicates.add(getQueryMultipleField("major", majorIds, criteriaBuilder, MajorJoin));
            }
            if (demandFilterDTO.getUniversityId() != null) {
                Integer universityId = demandFilterDTO.getUniversityId();
                predicates.add(criteriaBuilder.equal(PartnerUniversityJoin.get("university"), universityId));
            }

            return criteriaBuilder.and(predicates.toArray(new javax.persistence.criteria.Predicate[predicates.size()]));

        });
    }

    private static Predicate getQueryMultipleField(String attribute, List<String> values,
            CriteriaBuilder criteriaBuilder,
            Join<?, ?> join) {

        Predicate nameConditions = null;
        for (String value : values) {
            if (nameConditions == null) {
                nameConditions = criteriaBuilder
                        .or(criteriaBuilder.equal(join.get(attribute), Integer.parseInt(value)));
            } else {
                nameConditions = criteriaBuilder.or(nameConditions,
                        criteriaBuilder.equal(join.get(attribute), Integer.parseInt(value)));
            }
        }
        return (Predicate) nameConditions;
    }

    private static Predicate getQueryProvince(int value, String field, CriteriaBuilder criteriaBuilder,
            Root<InternshipProgramme> root) {

        return (Predicate) criteriaBuilder.equal(root.get(field), value);
    }

    @Override
    public javax.persistence.criteria.Predicate toPredicate(Root<InternshipProgramme> root, CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder) {
        // TODO Auto-generated method stub
        return null;
    }
}

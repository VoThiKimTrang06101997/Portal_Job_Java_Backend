package com.r2s.findInternship.data.mapper;

import org.mapstruct.Mapper;

import com.r2s.findInternship.data.dto.CompanyDTO;
import com.r2s.findInternship.data.entity.Company;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { StatusMapper.class})
public interface CompanyMapper {
    @Mapping(source = "statusDTO", target = "status")
    Company toEntity(CompanyDTO companyDTO);

    @Mapping(source = "status", target = "statusDTO")
    CompanyDTO toDTO(Company company);

}

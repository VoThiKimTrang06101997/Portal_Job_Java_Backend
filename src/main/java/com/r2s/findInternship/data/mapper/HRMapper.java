package com.r2s.findInternship.data.mapper;

import org.mapstruct.Mapper;

import com.r2s.findInternship.data.dto.hr.HRCreationDTO;
import com.r2s.findInternship.data.dto.hr.HRDTO;
import com.r2s.findInternship.data.dto.hr.HRProfileDTO;
import com.r2s.findInternship.data.entity.HR;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { UserMapper.class, CompanyMapper.class})
public interface HRMapper {

	@Mapping(source = "userCreationDTO", target = "user")
	@Mapping(source = "hrOtherInfoDTO.position", target = "position")
	@Mapping(source = "hrOtherInfoDTO.companyDTO", target = "company")
    HR toEntity(HRCreationDTO hrCreationDTO);
	
	@Mapping(source = "userProfileDTO", target = "user")
	@Mapping(source = "position", target = "position")
	//@Mapping(source = "hrOtherInfoDTO.companyDTO", target = "company")
    HR toEntity(HRProfileDTO hrProfileDTO);

	@Mapping(source = "user", target = "userDTO")
	@Mapping(source = "position", target = "hrOtherInfoDTO.position")
	@Mapping(source = "company", target = "hrOtherInfoDTO.companyDTO")
    HRDTO toDTO(HR hr);


}

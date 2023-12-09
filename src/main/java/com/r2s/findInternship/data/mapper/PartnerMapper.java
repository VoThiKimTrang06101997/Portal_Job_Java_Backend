package com.r2s.findInternship.data.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { UniversityMapper.class })
public interface PartnerMapper {

	// Partner toEntity(PartnerCreationDTO partnerCreationDTO);

	// Partner toEntity(PartnerDTO partnerDTO);

	// @Mapping(source = "university", target = "universityDTO")
	// @Mapping(source = "user.firstName", target = "userDetailsDTO.firstName")
	// @Mapping(source = "user.lastName", target = "userDetailsDTO.lastName")
	// @Mapping(source = "user.email", target = "userDetailsDTO.email")
	// @Mapping(source = "user.phone", target = "userDetailsDTO.phone")
	// PartnerDTO toDTO(Partner partner);

	// UniversityPartnerDTOShow toEntityUniversityPartnerShow(Partner partner);
}

package com.r2s.findInternship.data.mapper;

import org.mapstruct.Mapper;

import com.r2s.findInternship.data.dto.RoleDTO;
import com.r2s.findInternship.data.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	Role toEntity(RoleDTO roleDTO);

	RoleDTO toDTO(Role role);
}

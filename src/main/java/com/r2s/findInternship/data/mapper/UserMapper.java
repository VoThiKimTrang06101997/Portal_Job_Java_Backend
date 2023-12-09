package com.r2s.findInternship.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.r2s.findInternship.data.dto.user.UserCreationDTO;
import com.r2s.findInternship.data.dto.user.UserDTO;
import com.r2s.findInternship.data.dto.user.UserProfileDTO;
import com.r2s.findInternship.data.entity.User;

@Mapper(componentModel = "spring", uses = { RoleMapper.class, StatusMapper.class })
public interface UserMapper {

	@Mapping(ignore = true, target = "password")
	@Mapping(constant = "true", target = "mailReceive")
	@Mapping(ignore = true, target = "role")
	@Mapping(ignore = true, target = "status")
	User toEntity(UserCreationDTO creationDTO);

	@Mapping(ignore = true, target = "role")
	@Mapping(ignore = true, target = "status")
	User toEntity(UserProfileDTO userProfileDTO);

	@Mapping(source = "role", target = "roleDTO")
	@Mapping(source = "status", target = "statusDTO")
	UserDTO toDTO(User user);

//	@Mapping(source = "roleDTO", target = "role")
//	@Mapping(source = "statusDTO", target = "status")
//	User toDTO(UserDTO userDTO);

	@Mapping(source = "roleDTO", target = "role")
	@Mapping(source = "statusDTO", target = "status")
	User toEntity(UserDTO userDTO);


}

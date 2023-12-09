package com.r2s.findInternship.data.dto.user;

import com.r2s.findInternship.data.dto.RoleDTO;
import com.r2s.findInternship.data.dto.StatusDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserDTO extends UserProfileDTO{
    private Long id;
    private RoleDTO roleDTO;
    private StatusDTO statusDTO;
}

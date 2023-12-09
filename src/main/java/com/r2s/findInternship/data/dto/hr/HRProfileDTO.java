package com.r2s.findInternship.data.dto.hr;

import javax.validation.constraints.NotNull;

import com.r2s.findInternship.data.dto.user.UserProfileDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class HRProfileDTO {
    @NotNull(message = "The hr's profile must not be null")
    private UserProfileDTO userProfileDTO;
    @NotNull(message = "The hr's position must not be null")
    private String position;
//    @NotNull(message = "The hr's other information must not be null")
//    private HROtherInfoDTO hrOtherInfoDTO;
}

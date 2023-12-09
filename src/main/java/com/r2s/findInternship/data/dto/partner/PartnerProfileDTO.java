package com.r2s.findInternship.data.dto.partner;

import javax.validation.constraints.NotNull;

import com.r2s.findInternship.data.dto.user.UserProfileDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PartnerProfileDTO {
    @NotNull(message = "The partner's profile must not be null")
    private UserProfileDTO userProfileDTO;
    @NotNull(message = "The partner's other information must not be null")
    private PartnerOtherInfoDTO partnerOtherInfoDTO;
}

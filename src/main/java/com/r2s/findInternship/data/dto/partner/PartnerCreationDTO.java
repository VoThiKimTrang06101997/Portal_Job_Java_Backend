package com.r2s.findInternship.data.dto.partner;

import javax.validation.constraints.NotNull;

import com.r2s.findInternship.data.dto.user.UserCreationDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PartnerCreationDTO {
    @NotNull(message = "The user's information must not be null")
    private UserCreationDTO userCreationDTO;
    @NotNull(message = "The partner's other information must not be null")
    private PartnerOtherInfoDTO partnerOtherInfoDTO;
}

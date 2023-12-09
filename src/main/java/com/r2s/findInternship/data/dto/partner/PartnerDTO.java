package com.r2s.findInternship.data.dto.partner;

import com.r2s.findInternship.data.dto.user.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PartnerDTO {
    private Long id;
    private UserDTO userDTO;
    private PartnerOtherInfoDTO partnerOtherInfoDTO;
}

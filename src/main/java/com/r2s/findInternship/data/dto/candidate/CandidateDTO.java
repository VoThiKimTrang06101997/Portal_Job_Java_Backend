package com.r2s.findInternship.data.dto.candidate;

import com.r2s.findInternship.data.dto.user.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CandidateDTO {
    private Long id;
    private UserDTO userDTO;
    private CandidateOtherInfoDTO candidateOtherInfoDTO;
}

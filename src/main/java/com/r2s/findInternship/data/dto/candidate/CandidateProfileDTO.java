package com.r2s.findInternship.data.dto.candidate;

import javax.validation.constraints.NotNull;

import com.r2s.findInternship.data.dto.user.UserProfileDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CandidateProfileDTO {
    @NotNull(message = "The candidate profile must not be null")
    private UserProfileDTO userProfileDTO;
    private CandidateOtherInfoDTO candidateOtherInfoDTO;
}

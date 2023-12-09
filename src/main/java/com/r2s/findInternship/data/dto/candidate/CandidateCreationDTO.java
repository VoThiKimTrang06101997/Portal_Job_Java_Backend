package com.r2s.findInternship.data.dto.candidate;

import javax.validation.constraints.NotNull;

import com.r2s.findInternship.data.dto.user.UserCreationDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CandidateCreationDTO {
    @NotNull(message = "The user's information must not be null")
    private UserCreationDTO userCreationDTO;
    private CandidateOtherInfoDTO candidateOtherInfoDTO;
}

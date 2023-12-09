package com.r2s.findInternship.data.dto.partner;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.r2s.findInternship.data.dto.UniversityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PartnerOtherInfoDTO implements Serializable{
    @NotNull(message = "The partner's position must not be null")
    private String position;
    @NotNull(message = "The partner's university must not be null")
    private UniversityDTO universityDTO;
}

package com.r2s.findInternship.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InternshipPositionDTO {
    private Long id;
    private PositionDTO jobPositionDTO;
    private InternshipProgrammeDTO universityDemandDTO;
}


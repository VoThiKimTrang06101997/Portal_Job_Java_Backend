package com.r2s.findInternship.data.dto;

import lombok.Data;

@Data
public class InternshipScheduleDTO {
    private Long id;
    private ScheduleDTO jobTypeDTO;
    private InternshipProgrammeDTO universityDemandDTO;
}

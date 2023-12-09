package com.r2s.findInternship.data.dto;

import com.r2s.findInternship.data.dto.JobDTO;
import com.r2s.findInternship.data.dto.MajorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobMajorDTO {
    private JobDTO jobDTO;

    private MajorDTO majorDTO;
}
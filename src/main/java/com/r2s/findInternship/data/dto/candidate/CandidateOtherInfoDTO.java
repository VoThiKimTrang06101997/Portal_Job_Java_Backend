package com.r2s.findInternship.data.dto.candidate;

import java.util.List;

import com.r2s.findInternship.data.dto.PositionDTO;
import com.r2s.findInternship.data.dto.ScheduleDTO;
import com.r2s.findInternship.data.dto.MajorDTO;
import com.r2s.findInternship.data.dto.UniversityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CandidateOtherInfoDTO {
    private UniversityDTO universityDTO;
    private String CV;
    private String referenceLetter;
    private boolean searchable; // a candidate's status permitting hr to search and contact
    private List<PositionDTO> positionDTOs;
    private List<MajorDTO> majorDTOs;
    private List<ScheduleDTO> scheduleDTOs;      
    private String desiredJob;
    private String desiredWorkingProvince;

}

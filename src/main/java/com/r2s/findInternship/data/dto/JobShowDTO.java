package com.r2s.findInternship.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class JobShowDTO {
    private int id;
    private String name;
    private CompanyDTO companyDTO;
    private List<PositionDTO> positionDTOs;
    private List<MajorDTO> majorDTOs;
    private List<ScheduleDTO> scheduleDTOs;
    private int amount;
    private Date startDate;
    private Date endDate;
    private long salaryMin;
    private long salaryMax;
    private String description;
    private String requirement;
    private String otherInfo;
    private String location;
}

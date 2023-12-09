package com.r2s.findInternship.data.dto.show;

import java.util.Date;
import java.util.List;

import com.r2s.findInternship.data.dto.InternshipPositionDTO;
import com.r2s.findInternship.data.dto.InternshipScheduleDTO;
import com.r2s.findInternship.data.dto.InternshipMajorDTO;
import com.r2s.findInternship.data.dto.StatusDTO;
import com.r2s.findInternship.data.dto.partner.PartnerDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UniversityDemandDTOShow {
	private int id;
	private String title;
	private String description;
	private String requirement;
	private String otherInfo;
	private Date endDate;
	private String students;
	private Date createdDate;
	private Date lastModifiedDate;
	private PartnerDTO PartnerDTO;
	private List<InternshipPositionDTO> jobPositionDemandDTOs;
	private List<InternshipScheduleDTO> jobTypeDemandDTOs;
	private List<InternshipMajorDTO> majorDemandDTOs;
	private long amount;
	private StatusDTO statusDTO;
}















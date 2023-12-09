package com.r2s.findInternship.data.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.data.dto.partner.PartnerDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class InternshipProgrammeDTO implements Serializable {
	private int id;
	private String name;
	private PartnerDTO partnerDTO;
	private List<MajorDTO> majorDTOs;
	private String description;
	private List<PositionDTO> jobPositionDTOs;
	private String requirement;
	private String otherInfo;
	private Date startDate;
	private Date endDate;
	private Date updatedDate;
	private String students; //link files excel list of students
	private Date createdDate;
	private MultipartFile file;
	private StatusDTO statusDTO;
	private List<ScheduleDTO> jobTypeDTOs;
	private long amount;
}

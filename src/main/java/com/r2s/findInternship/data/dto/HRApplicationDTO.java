package com.r2s.findInternship.data.dto;

import java.io.Serializable;
import java.util.Date;

import com.r2s.findInternship.data.dto.hr.HRDTO;
import com.r2s.findInternship.data.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HRApplicationDTO implements Serializable {
	private int id;
	private InternshipProgrammeDTO universityDemandDTO;
	private HRDTO hrDTO;
	private Date createdDate;
	private Date lastModifiedDate;
	private Status status;
	private String note;
}

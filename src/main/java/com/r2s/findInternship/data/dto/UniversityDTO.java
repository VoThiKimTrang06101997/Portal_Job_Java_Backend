package com.r2s.findInternship.data.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UniversityDTO implements Serializable {
	private Long id;
	private String name;
	private String avatar;
	private String shortName;
	private String description;	
	private String website;
	private String email;
	private String phone;
	private UniversityTypeDTO universityTypeDTO;
	private String location;
	private StatusDTO statusDTO;
	private Date createdDate;
}

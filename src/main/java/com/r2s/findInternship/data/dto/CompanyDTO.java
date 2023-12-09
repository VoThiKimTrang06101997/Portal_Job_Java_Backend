package com.r2s.findInternship.data.dto;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.r2s.findInternship.constant.Constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyDTO implements Serializable {
	private Long id;
	private String name;
	private String logo;
	private String description;
	private String website;
	private String email;
	private String phone;
	private String tax;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_FORMAT)
	private Date createdDate;
	private String location;
	private int personnelSize;
	private StatusDTO statusDTO;
}

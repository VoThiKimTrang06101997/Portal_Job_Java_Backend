package com.r2s.findInternship.data.dto.show;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.r2s.findInternship.constant.Constant;
import com.r2s.findInternship.data.dto.JobDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationDTONotShowCandidate {
	private int id;
	private JobDTO jobDTO;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
	private Date createdDate;
	private String referenceLetter;
	private String CV;

}

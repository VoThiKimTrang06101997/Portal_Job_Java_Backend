package com.r2s.findInternship.data.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestCandidateDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer[] majorIds;
	private Integer[] jobTypeIds;
	private Integer[] jobPositionIds;
	private Integer desiredWorkingProvinceId;	
	private String desiredJob;	


}

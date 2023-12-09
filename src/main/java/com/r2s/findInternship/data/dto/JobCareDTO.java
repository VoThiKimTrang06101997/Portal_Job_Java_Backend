package com.r2s.findInternship.data.dto;
import java.io.Serializable;

import com.r2s.findInternship.data.dto.candidate.CandidateDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobCareDTO implements Serializable {
	private int id;
	private JobDTO jobDTO;
	private CandidateDTO candidateDTO;
	
}

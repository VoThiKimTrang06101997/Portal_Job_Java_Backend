package com.r2s.findInternship.data.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CandidateFilterByHRDTO implements Serializable{
    private String desiredJob;
    private String desiredWorkingProvince;
    private List<Integer> scheduleIds;
    private List<Integer> positionIds;
    private List<Integer> majorIds;
}

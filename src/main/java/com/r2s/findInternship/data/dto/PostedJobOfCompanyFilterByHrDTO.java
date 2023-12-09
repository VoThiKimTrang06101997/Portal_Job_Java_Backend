package com.r2s.findInternship.data.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PostedJobOfCompanyFilterByHrDTO implements Serializable {
    private String quickSearch;
    private String provinceName;
    private Date endDate;
    private Integer statusId; 
}
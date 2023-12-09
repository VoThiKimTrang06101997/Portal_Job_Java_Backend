package com.r2s.findInternship.data.dto.hr;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.r2s.findInternship.data.dto.CompanyDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class HROtherInfoDTO implements Serializable{
    @NotNull(message = "The hr's position must not be null")
    private String position;
    @NotNull(message = "The hr's company must not be null")
    private CompanyDTO companyDTO;
}

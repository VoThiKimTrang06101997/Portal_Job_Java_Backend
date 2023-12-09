package com.r2s.findInternship.data.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InternshipProgrammeFilterDTO implements Serializable {

    private String name;
    private List<String> universityTypeIds;
    private List<String> majorIds;
    private Integer universityId;
    private String order;
}

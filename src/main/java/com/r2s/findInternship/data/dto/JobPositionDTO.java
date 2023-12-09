package com.r2s.findInternship.data.dto;

import com.r2s.findInternship.data.dto.JobDTO;
import com.r2s.findInternship.data.dto.PositionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobPositionDTO implements Serializable {
    private JobDTO jobDTO;

    private PositionDTO positionDTO;
}

package com.r2s.findInternship.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobScheduleDTO implements Serializable {
    private JobDTO jobDTO;

    private ScheduleDTO scheduleDTO;
}


package com.r2s.findInternship.data.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class MajorDTO implements Serializable {
	private int id;
	private String name;
}

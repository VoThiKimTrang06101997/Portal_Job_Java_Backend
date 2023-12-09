package com.r2s.findInternship.data.dto;

import java.io.Serializable;
import java.util.Date;

import com.r2s.findInternship.data.dto.user.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
	private int id;
    private UserDTO userDTO;
    private CompanyDTO companyDTO;
    private int score;
    private String title;
    private String comment;
    private Date createdDate;
    private StatusDTO statusDTO;
    private boolean hide;
}

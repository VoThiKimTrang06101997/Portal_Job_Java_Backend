package com.r2s.findInternship.data.dto.user;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.r2s.findInternship.constant.Constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserProfileDTO implements Serializable {
    
	@Email(message = "{error.emailFormat}")
	@NotEmpty(message = "{error.emailNotNull}")
    private String email;
    private String firstName;
    private String lastName;
    private Boolean gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_FORMAT)
    private Date birthDay;
    private String phone;
    private String avatar;
    private String location;
	private boolean mailReceive; 

}

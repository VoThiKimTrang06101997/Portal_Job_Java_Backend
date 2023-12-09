package com.r2s.findInternship.data.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePasswordDTO implements Serializable {
	@NotNull(message = "{error.userNewPassword}")
	private String newPassword;
	@NotNull(message = "{error.userOldPassword}")
	private String oldPassword;
}

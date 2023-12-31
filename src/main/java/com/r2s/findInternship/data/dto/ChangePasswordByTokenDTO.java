package com.r2s.findInternship.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePasswordByTokenDTO implements Serializable {
    @NotNull(message = "{error.token}")
    private String token;
    @NotNull(message = "{error.userNewPassword}")
    private String newPassword;
}

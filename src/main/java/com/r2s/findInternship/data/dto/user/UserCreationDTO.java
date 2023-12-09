package com.r2s.findInternship.data.dto.user;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserCreationDTO extends UserProfileDTO {
    @NotEmpty(message = "{error.passwordNotNull}")
    @Size(min = 6, message = "{error.passwordRequire}")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).+$", message = "{error.passwordRegex}")
    private String password;
}

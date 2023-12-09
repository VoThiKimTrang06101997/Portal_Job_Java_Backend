package com.r2s.findInternship.controller;

import javax.validation.Valid;

import com.r2s.findInternship.common.MessageResponse;
import com.r2s.findInternship.constant.ApiURL;
import com.r2s.findInternship.constant.PageDefault;
import com.r2s.findInternship.service.MailService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.r2s.findInternship.data.dto.ChangePasswordByTokenDTO;
import com.r2s.findInternship.data.dto.ChangePasswordDTO;
import com.r2s.findInternship.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.USER)
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    @GetMapping("/forgot-password/{email}")
    public ResponseEntity<?> forgotPassword(@PathVariable String email,
                                            @RequestParam(value = "flag", defaultValue = "false") boolean flag){//android: true
        return ResponseEntity.ok(mailService.sendMailForgotPassword(email, flag));
    }

    @SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize(value = "isAuthenticated()")
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO passwordChangeDTO) {
        this.userService.changePassword(passwordChangeDTO);

        return ResponseEntity.ok(new MessageResponse(200, "đổi mật khẩu thành công", null));

    }

    @PostMapping("/change-password-by-token")
    public ResponseEntity<?> changePasswordByToken(
            @Valid @RequestBody ChangePasswordByTokenDTO changePasswordByTokenDTO) {
        this.userService.changePasswordByToken(changePasswordByTokenDTO);
        return ResponseEntity.ok(new MessageResponse(200, "đổi mật khẩu thành công", null));
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> findAllByUsernameLike(@PathVariable String username,
                                                   @RequestParam(defaultValue = PageDefault.NO) int no, @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
        return ResponseEntity.ok(userService.findAllByEmailLike(username, no, limit));
    }

    @SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize(value = "isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<?> UpdateMailReceive(@PathVariable long id){
        return ResponseEntity.ok(userService.updateMailReceive(id));
    }

}

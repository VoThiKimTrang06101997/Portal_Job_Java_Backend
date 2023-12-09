package com.r2s.findInternship.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import com.r2s.findInternship.common.JwtUtils;
import com.r2s.findInternship.data.dto.JwtResponseDTO;
import com.r2s.findInternship.data.dto.LoginRequestDTO;
import com.r2s.findInternship.data.entity.User;
import com.r2s.findInternship.service.UserService;
import com.r2s.findInternship.service.impl.UserDetailsImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
//@RequestMapping(ApiURL.LOGIN)
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserService userService;

    public final Logger LOGGER = LoggerFactory.getLogger("info");
    @Value("${url.redirect.path}")
    private String urlOAuth2;

    @PostMapping("api/login")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(),
                        loginRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        User userDto = userService.findByEmail(user.getEmail());
        String message = userService.checkStatusUser(userDto.getStatus());
        long id = userDto.getId();
        List<String> roles = user.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        LOGGER.info("%s has successfully logged in.", user.getEmail());
        return new ResponseEntity<JwtResponseDTO>(
                new JwtResponseDTO(jwt, user.getEmail(), roles.get(0),
                        userDto.getAvatar(), id, message.isEmpty() ? null : message),
                HttpStatus.CREATED);
    }

    @GetMapping("/")
    public RedirectView loginOauthGoogleSuccess(@RequestParam(value = "token") String token,
                                                @RequestParam("type") String type, @RequestParam("email") String email,
                                                @RequestParam("role") String role, @RequestParam("avatar") String avatar,
                                                @RequestParam("id") int id) {
        String redirectUrl = "http://localhost:3000/auth/social/google?token=" + token + "&type=" + type
                + "&email=" + email + "&role=" + role + "&avatar=" + avatar + "&id=" + id;
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrl);
        return redirectView;

    }

    @GetMapping("/failure")
    public ResponseEntity<?> loginFailure() {
        return new ResponseEntity<>("Có lỗi xảy ra", HttpStatus.BAD_REQUEST);
    }

}

package com.r2s.findInternship.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
	 private static final Logger LOGGER = LoggerFactory.getLogger("exception");
	 //HANDLE UNAUTHORIED
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		LOGGER.error("Unauthorized error: {}",authException.getMessage());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		final Map<String, Object> BODY = new HashMap<>();
        if (authException instanceof InsufficientAuthenticationException) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            BODY.put("status", HttpServletResponse.SC_FORBIDDEN);
            BODY.put("error", "Forbidden!");
            BODY.put("message", "Forbidden");
        } else {
            BODY.put("status", HttpStatus.UNAUTHORIZED.value());
            BODY.put("error", "Không có quyền");
        }
        BODY.put("message", authException.getMessage());
        BODY.put("path", request.getServletPath());
        final ObjectMapper MAPPER = new ObjectMapper();
        MAPPER.writeValue(response.getOutputStream(), BODY);
	}
}

package com.baranozdeniz.personalwebsite.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuthException implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        String json = String.format(
                "{" +
                        "\"type\": \"about:blank\"," +
                        "\"title\": \"AuthenticationException\"," +
                        "\"status\": %d," +
                        "\"detail\": \"UNAUTHORIZED\"," +
                        "\"instance\": \"uri=%s\"," +
                        "\"timestamp\": \"%s\"" +
                        "}",
                HttpStatus.UNAUTHORIZED.value(),
                request.getRequestURI(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
        response.getWriter().write(json);
    }

}

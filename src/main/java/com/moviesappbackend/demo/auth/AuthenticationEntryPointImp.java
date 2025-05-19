package com.moviesappbackend.demo.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviesappbackend.demo.entities.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationEntryPointImp implements AuthenticationEntryPoint {

    @Autowired
    private final ObjectMapper objMapper; // convert ErrorResponse to json

    public AuthenticationEntryPointImp(ObjectMapper objMapper) {
        this.objMapper = objMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

      response.setContentType("application/json");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, authException.getMessage());

        // convert error response to json and write it to response
        response.getWriter().write(objMapper.writeValueAsString(errorResponse));
    }
}

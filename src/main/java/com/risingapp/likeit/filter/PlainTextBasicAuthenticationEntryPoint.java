package com.risingapp.likeit.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.risingapp.likeit.model.common.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
public class PlainTextBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatusCode(1);
        messageResponse.setErrorMessage("User is unauthorized");
        messageResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
        writer.println(mapper.writeValueAsString(messageResponse));
    }
}

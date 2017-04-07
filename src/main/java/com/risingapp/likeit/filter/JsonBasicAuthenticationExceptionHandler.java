package com.risingapp.likeit.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.risingapp.likeit.enums.ErrorStatus;
import com.risingapp.likeit.model.common.MessageResponse;
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
public class JsonBasicAuthenticationExceptionHandler extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException, IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        writer.println(mapper.writeValueAsString(new MessageResponse(ErrorStatus.NOT_AUTHORIZED)));
    }
}

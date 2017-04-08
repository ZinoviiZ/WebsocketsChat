package com.risingapp.likeit.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.risingapp.likeit.enums.ErrorStatus;
import com.risingapp.likeit.model.common.MessageResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
public class RESTAccessDeniedExceptionHandler extends AccessDeniedHandlerImpl {

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        PrintWriter writer = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        writer.println(mapper.writeValueAsString(new MessageResponse(ErrorStatus.FORBIDDEN)));
    }
}

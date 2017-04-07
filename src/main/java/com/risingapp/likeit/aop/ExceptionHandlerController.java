package com.risingapp.likeit.aop;

import com.risingapp.likeit.model.common.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
@ControllerAdvice(basePackages = {"com.risingapp.likeit.rest"})
public class ExceptionHandlerController {

//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseBody
//    public String exception(AccessDeniedException e) {
//        return "{\"status\":\"access denied\"}";
//    }
//
//    @ExceptionHandler(BadCredentialsException.class)
//    @ResponseBody
//    public String exception(BadCredentialsException e) {
//        return "{\"status\":\"access denied\"}";
//    }

    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody MessageResponse handleGeneralException(HttpServletRequest req, Exception ex) {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatusCode(1);
        messageResponse.setErrorMessage("Internal server error: " + ex.getLocalizedMessage());
        return messageResponse;
    }
}

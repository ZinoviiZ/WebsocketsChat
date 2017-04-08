package com.risingapp.likeit.aop;

import com.risingapp.likeit.enums.ErrorStatus;
import com.risingapp.likeit.execption.AuthenticationFailureException;
import com.risingapp.likeit.execption.SessionTimeOutException;
import com.risingapp.likeit.execption.UserWithThisEmailExists;
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

    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody MessageResponse handleGeneralException(HttpServletRequest req, Exception ex) {
        return new MessageResponse(ErrorStatus.INTERNAL_ERROR);
    }

    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody MessageResponse handleUserWithThisEmailExists(HttpServletRequest req, UserWithThisEmailExists ex) {
        return new MessageResponse(ErrorStatus.EMAIL_ALREADY_REGISTERED);
    }

    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody MessageResponse handleAuthenticationFailureException(HttpServletRequest req, AuthenticationFailureException ex) {
        return new MessageResponse(ErrorStatus.AUTHORIZE_FAILURE);

    }

    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody MessageResponse handleSessionTimeOutException(HttpServletRequest req, SessionTimeOutException ex) {
        return new MessageResponse(ErrorStatus.SESSION_TIME_OUT);

    }
}

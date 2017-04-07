package com.risingapp.likeit.rest;

import com.risingapp.likeit.execption.AuthenticationFailureException;
import com.risingapp.likeit.model.common.MessageResponse;
import com.risingapp.likeit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
@Controller
public class LoginController {

    @Autowired private UserService userService;

    @RequestMapping(value = "/login/success")
    public @ResponseBody MessageResponse loginSucess() {
        return userService.getCurrentUser();
    }

    @RequestMapping(value = "/login/failure")
    public void loginFailure() throws AuthenticationFailureException {
        throw new AuthenticationFailureException();
    }

    @RequestMapping(value = "/logout/success")
    public @ResponseBody MessageResponse logout() {
        return new MessageResponse();
    }
}

package com.risingapp.likeit.rest;

import com.risingapp.likeit.model.response.UserResponse;
import com.risingapp.likeit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public @ResponseBody UserResponse loginSucess() {
        return userService.getCurrentUser();
    }

    @RequestMapping(value = "/login/failure")
    public @ResponseBody ResponseEntity loginFailure() {
        return new ResponseEntity(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
    }

    @RequestMapping(value = "/logout/success")
    public @ResponseBody ResponseEntity logout() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/unauthorized")
    public @ResponseBody String unauthorized() {
        return "User is having enough rules";
    }
}

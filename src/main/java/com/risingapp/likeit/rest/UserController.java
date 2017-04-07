package com.risingapp.likeit.rest;

import com.risingapp.likeit.execption.UserWithThisEmailExists;
import com.risingapp.likeit.model.common.MessageResponse;
import com.risingapp.likeit.model.request.UserRequest;
import com.risingapp.likeit.model.response.UserResponse;
import com.risingapp.likeit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zinoviyzubko on 05.04.17.
 */
@RestController
@RequestMapping(value = "/rest/user")
public class UserController {

    @Autowired private UserService userService;

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public UserResponse getCurrent() {
        return userService.getCurrentUser();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserResponse getUserById(@PathVariable("id") Long userId) {
        return userService.getUserById(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public MessageResponse registration(@RequestBody UserRequest request) throws UserWithThisEmailExists {
        return userService.saveUser(request);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public MessageResponse updateUser(@PathVariable("id") Long userId, @RequestBody UserRequest request) {
        return userService.updateUser(userId, request);
    }
}

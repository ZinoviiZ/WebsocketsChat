package com.risingapp.likeit.rest;

import com.risingapp.likeit.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zinoviyzubko on 05.04.17.
 */
@RestController
@RequestMapping(value = "/rest/user")
public class UserController {

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public ResponseEntity getCurrent() {
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getUserById(@PathVariable("id") Long userId) {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity registration(User user) {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity updateUser() {
        return null;
    }
}

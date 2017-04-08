package com.risingapp.likeit.convertor.request;

import com.risingapp.likeit.entity.User;
import com.risingapp.likeit.model.request.UserRequest;
import org.springframework.stereotype.Component;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
@Component
public class UserRequestConverter {

    public User convert(UserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBirthday(request.getBirthday());
        return user;
    }
}

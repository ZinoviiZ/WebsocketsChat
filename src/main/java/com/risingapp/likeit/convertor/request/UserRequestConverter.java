package com.risingapp.likeit.convertor.request;

import com.risingapp.likeit.entity.User;
import com.risingapp.likeit.enums.UserRole;
import com.risingapp.likeit.model.request.UserRequest;
import org.springframework.stereotype.Component;

import java.util.Date;

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
        user.setRegistrationDate(new Date().getTime());
        user.setLastVisit(new Date().getTime());
        user.setUserRole(UserRole.USER);
        return user;
    }
}

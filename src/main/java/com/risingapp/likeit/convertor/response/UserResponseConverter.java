package com.risingapp.likeit.convertor.response;

import com.risingapp.likeit.entity.User;
import com.risingapp.likeit.model.response.UserResponse;
import org.springframework.stereotype.Component;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
@Component
public class UserResponseConverter {

    public UserResponse convert(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setRegistrationDate(user.getRegistrationDate());
        response.setBirthday(user.getBirthday());
        return response;
    }
}

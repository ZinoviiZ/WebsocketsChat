package com.risingapp.likeit.model.request;

import com.risingapp.likeit.enums.UserGender;
import com.risingapp.likeit.model.common.ApiRequest;
import lombok.Data;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
@Data
public class UserRequest extends ApiRequest {

    private String email;
    private String password;

    private String firstName;
    private String lastName;
    private Long birthday;
    private UserGender gender;
}

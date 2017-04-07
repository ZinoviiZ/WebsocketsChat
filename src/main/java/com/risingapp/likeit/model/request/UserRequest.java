package com.risingapp.likeit.model.request;

import lombok.Data;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
@Data
public class UserRequest {

    private String email;
    private String password;

    private String firstName;
    private String lastName;
    private String birthday;
}

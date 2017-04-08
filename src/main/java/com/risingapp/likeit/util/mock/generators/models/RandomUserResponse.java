package com.risingapp.likeit.util.mock.generators.models;

import lombok.Data;

/**
 * Created by oleg on 31.03.17.
 */
@Data
public class RandomUserResponse {
    private RandomUserName name;
    private RandomUserLogin login;
    private RandomUserPicture picture;
    private String registered;
    private String dob;
    private String email;



}

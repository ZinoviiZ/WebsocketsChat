package com.risingapp.likeit.model.response;

import com.risingapp.likeit.model.common.ApiResponse;
import lombok.Data;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Data
public class ChangeProfileResponse extends ApiResponse {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Long birthday;
    private String photoUrl;
    private String phoneNumber;
}

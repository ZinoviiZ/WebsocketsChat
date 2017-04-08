package com.risingapp.likeit.model.response;

import com.risingapp.likeit.model.common.ApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserResponse extends ApiResponse {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Long birthday;
    private Long registrationDate;
}

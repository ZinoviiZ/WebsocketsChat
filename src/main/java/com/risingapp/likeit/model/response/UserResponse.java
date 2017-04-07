package com.risingapp.likeit.model.response;

import com.risingapp.likeit.model.common.MessageResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserResponse extends MessageResponse {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String birthday;
    private String registrationDate;
}

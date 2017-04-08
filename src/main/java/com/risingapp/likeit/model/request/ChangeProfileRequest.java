package com.risingapp.likeit.model.request;

import com.risingapp.likeit.model.common.ApiRequest;
import lombok.Data;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Data
public class ChangeProfileRequest extends ApiRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Long birthday;
    private Long photoId;
    private String phoneNumber;
}

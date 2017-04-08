package com.risingapp.likeit.model.request;

import com.risingapp.likeit.model.common.ApiRequest;
import lombok.Data;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Data
public class ChangeSettingRequest extends ApiRequest {

    private Boolean showEmail;
    private Boolean showBirthday;
    private Boolean showPhoneNumber;
}

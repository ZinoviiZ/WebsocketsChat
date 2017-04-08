package com.risingapp.likeit.model.response;

import com.risingapp.likeit.model.common.ApiResponse;
import lombok.Data;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Data
public class ChangeSettingResponse extends ApiResponse {

    private Boolean showEmail;
    private Boolean showBirthday;
    private Boolean showPhoneNumber;
}

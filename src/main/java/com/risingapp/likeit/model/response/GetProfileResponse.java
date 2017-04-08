package com.risingapp.likeit.model.response;

import lombok.Data;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Data
public class GetProfileResponse extends GetMyProfileResponse {

    private Long lastVisit;
}

package com.risingapp.likeit.model.response;

import com.risingapp.likeit.model.common.ApiResponse;
import lombok.Data;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Data
public class SavePhotoResponse extends ApiResponse {

    private Long photoId;
    private String photoUrl;
}

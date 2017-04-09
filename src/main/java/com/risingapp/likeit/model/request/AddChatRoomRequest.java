package com.risingapp.likeit.model.request;

import com.risingapp.likeit.model.common.ApiRequest;
import lombok.Data;

import java.util.List;

/**
 * Created by zinoviyzubko on 09.04.17.
 */
@Data
public class AddChatRoomRequest extends ApiRequest {

    private List<Long> userIds;
    private Long photoId;
    private String name;
}
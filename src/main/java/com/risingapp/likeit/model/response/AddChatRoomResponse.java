package com.risingapp.likeit.model.response;

import com.risingapp.likeit.model.common.ApiResponse;
import lombok.Data;

import java.util.List;

/**
 * Created by zinoviyzubko on 09.04.17.
 */
@Data
public class AddChatRoomResponse extends ApiResponse {

    private Long roomId;
    private List<Long> userIds;
    private String name;
    private String photoUrl;
}

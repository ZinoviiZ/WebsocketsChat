package com.risingapp.likeit.model.response;

import lombok.Data;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Data
public class ChatRoomResponse {

    private String chatName;
    private Integer usersCount;
    private String lastMessageAuthorName;
    private String lastMessage;
    private Long sendTime;
}

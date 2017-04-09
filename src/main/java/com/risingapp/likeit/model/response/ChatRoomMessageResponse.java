package com.risingapp.likeit.model.response;

import lombok.Data;

import java.util.List;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Data
public class ChatRoomMessageResponse {

    private Long messageId;
    private String authorPhotoUrl;
    private String authorName;
    private String text;
    private Long sendTime;
    private Boolean isLike;
    private Boolean own;
    private Integer likes;
    private List<AttachmentsResponse> attachments;
}

package com.risingapp.likeit.model.response;

import lombok.Data;

import java.util.List;

/**
 * Created by zinoviyzubko on 09.04.17.
 */
@Data
public class SendMessageResponse {

    private Long messageId;
    private String authorName;
    private String authorPhotoUrl;
    private List<AttachmentModel> attachments;
    private Long time;

}

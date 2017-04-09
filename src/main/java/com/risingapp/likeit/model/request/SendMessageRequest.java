package com.risingapp.likeit.model.request;

import com.risingapp.likeit.model.common.ApiRequest;
import com.risingapp.likeit.model.response.AttachmentModel;
import lombok.Data;

import java.util.List;

/**
 * Created by zinoviyzubko on 09.04.17.
 */
@Data
public class SendMessageRequest extends ApiRequest {

    private String text;
    private List<AttachmentModel> attachments;
    private Long time;
}

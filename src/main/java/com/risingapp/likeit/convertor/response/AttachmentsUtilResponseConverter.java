package com.risingapp.likeit.convertor.response;

import com.risingapp.likeit.entity.Attachment;
import com.risingapp.likeit.model.response.AttachmentsResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 09.04.17.
 */
public class AttachmentsUtilResponseConverter
{
    public List<AttachmentsResponse> buildAttachments(List<Attachment> attachments) {
        List<AttachmentsResponse> attachmentsResponses = new ArrayList<>();
        for (Attachment attachment: attachments) {
            AttachmentsResponse attachmentsResponse = new AttachmentsResponse();
            attachmentsResponse.setUrl(attachment.getUrl());
            attachmentsResponse.setType(attachment.getType());
            attachmentsResponses.add(attachmentsResponse);
        }
        return attachmentsResponses;
    }
}

package com.risingapp.likeit.convertor.response;

import com.risingapp.likeit.entity.Attachment;
import com.risingapp.likeit.model.response.AttachmentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 09.04.17.
 */
public class AttachmentsUtilResponseConverter
{
    public List<AttachmentModel> buildAttachments(List<Attachment> attachments) {
        List<AttachmentModel> AttachmentModels = new ArrayList<>();
        for (Attachment attachment: attachments) {
            AttachmentModel AttachmentModel = new AttachmentModel();
            AttachmentModel.setUrl(attachment.getUrl());
            AttachmentModel.setType(attachment.getType());
            AttachmentModels.add(AttachmentModel);
        }
        return AttachmentModels;
    }
}

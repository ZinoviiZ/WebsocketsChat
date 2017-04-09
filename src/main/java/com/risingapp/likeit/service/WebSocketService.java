package com.risingapp.likeit.service;

import com.risingapp.likeit.entity.Attachment;
import com.risingapp.likeit.entity.ChatRoom;
import com.risingapp.likeit.entity.Message;
import com.risingapp.likeit.entity.User;
import com.risingapp.likeit.execption.SessionTimeOutException;
import com.risingapp.likeit.model.common.MessageResponse;
import com.risingapp.likeit.model.request.SendMessageRequest;
import com.risingapp.likeit.model.response.AttachmentModel;
import com.risingapp.likeit.model.response.SendMessageResponse;
import com.risingapp.likeit.repository.AttachmentRepository;
import com.risingapp.likeit.repository.ChatRoomRepository;
import com.risingapp.likeit.repository.MessageRepository;
import com.risingapp.likeit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zinoviyzubko on 09.04.17.
 */
@Service
public class WebSocketService extends ParentService {

    @Autowired private UserRepository userRepository;
    @Autowired private MessageRepository messageRepository;
    @Autowired private AttachmentRepository attachmentRepository;
    @Autowired private ChatRoomRepository chatRoomRepository;

    @Transactional
    public MessageResponse sendMessage(Long roomId, SendMessageRequest request) throws SessionTimeOutException {
        User user = getSessionUser();
        ChatRoom chatRoom = chatRoomRepository.findOne(roomId);
        Message message = convertMessageRequest(user, chatRoom, request);
        SendMessageResponse data = convertMessageResponse(message, user);
        return new MessageResponse(data);
    }

    @Transactional
    private Message convertMessageRequest(User user, ChatRoom chatRoom, SendMessageRequest request) {
        Message message = new Message();
        message.setText(request.getText());
        message.setChatRoom(chatRoom);
        message.setCreatedDate(request.getTime());
        message.setUser(user);
        messageRepository.save(message);
        message.setAttachments(convertAttachments(request.getAttachments(), message));
        return message;
    }

    @Transactional
    private List<Attachment> convertAttachments(List<AttachmentModel> attachmentModels, Message message) {
        List<Attachment> attachments = new ArrayList<>();
        for (AttachmentModel attachmentModel : attachmentModels) {
            Attachment attachment = new Attachment();
            attachment.setUrl(attachmentModel.getUrl());
            attachment.setType(attachmentModel.getType());
            attachment.setMessage(message);
            attachmentRepository.save(attachment);
            attachments.add(attachment);
        }
        return attachments;
    }

    private List<AttachmentModel> convertAttachments(List<Attachment> attachments) {
        List<AttachmentModel> attachmentModels = new ArrayList<>();
        for (Attachment attachment : attachments) {
            AttachmentModel attachmentModel = new AttachmentModel();
            attachmentModel.setType(attachment.getType());
            attachmentModel.setUrl(attachment.getUrl());
            attachmentModels.add(attachmentModel);
        }
        return attachmentModels;
    }

    @Transactional
    private SendMessageResponse convertMessageResponse(Message message, User user) {
        SendMessageResponse response = new SendMessageResponse();
        response.setAuthorName(user.getFirstName() + " " + user.getLastName());
        response.setAuthorPhotoUrl(user.getPhoto().getPhotoUrl());
        response.setMessageId(message.getId());
        response.setTime(message.getCreatedDate());
        response.setAttachments(convertAttachments(message.getAttachments()));
        return response;
    }
}

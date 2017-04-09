package com.risingapp.likeit.convertor.response;

import com.risingapp.likeit.entity.ChatRoom;
import com.risingapp.likeit.entity.Message;
import com.risingapp.likeit.entity.MessageLike;
import com.risingapp.likeit.entity.User;
import com.risingapp.likeit.model.response.ChatRoomMessageResponse;
import com.risingapp.likeit.model.response.ChatRoomResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zinoviyzubko on 09.04.17.
 */
@Component
public class ChatUtilResponseConverter {

    public List<ChatRoomResponse> buildChatRooms(List<ChatRoom> chatRooms) {
        List<ChatRoomResponse> chatRoomResponses = new ArrayList<>();
        for (ChatRoom chatRoom : chatRooms) {
            ChatRoomResponse chatRoomResponse = new ChatRoomResponse();
            chatRoomResponse.setRoomId(chatRoom.getId());
            chatRoomResponse.setChatName(chatRoom.getName());
            chatRoomResponse.setUsersCount(chatRoom.getUsers().size());
            if (chatRoom.getMessages() != null) {
                Message lastMessage = chatRoom.getMessages().get(chatRoom.getMessages().size() - 1);
                chatRoomResponse.setLastMessage(lastMessage.getText());
                chatRoomResponse.setLastMessageAuthorName(lastMessage.getUser().getFirstName() + " " + lastMessage.getUser().getFirstName());
                chatRoomResponse.setSendTime(lastMessage.getCreatedDate());
            }
            chatRoomResponses.add(chatRoomResponse);
        }
        return chatRoomResponses;
    }

    public List<ChatRoomMessageResponse> buildChatMessages(User user, List<Message> messages) {
        List<ChatRoomMessageResponse> messageResponses = new ArrayList<>();
        for (Message message : messages) {
            ChatRoomMessageResponse messageResponse = new ChatRoomMessageResponse();
            messageResponse.setMessageId(message.getId());
            if (message.getUser().getPhoto() != null)
                messageResponse.setAuthorPhotoUrl(message.getUser().getPhoto().getPhotoUrl());
            messageResponse.setAuthorName(message.getUser().getFirstName() + " " + message.getUser().getFirstName());
            messageResponse.setText(message.getText());
            messageResponse.setSendTime(message.getCreatedDate());
            messageResponse.setLikes(message.getLikes().size());

            messageResponse.setAttachments(
                    new AttachmentsUtilResponseConverter().buildAttachments(message.getAttachments()));
            if (checkLike(user, message))
                messageResponse.setIsLike(true);
            messageResponses.add(messageResponse);
        }
        return messageResponses;
    }

    private boolean checkLike(User user, Message message) {
        for(MessageLike like : user.getLikes()) {
            if (like.getMessage().equals(message))
                return true;
        }
        return false;
    }
}
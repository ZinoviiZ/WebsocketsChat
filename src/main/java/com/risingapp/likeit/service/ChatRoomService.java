package com.risingapp.likeit.service;

import com.risingapp.likeit.convertor.response.ChatUtilResponseConverter;
import com.risingapp.likeit.entity.ChatRoom;
import com.risingapp.likeit.entity.Message;
import com.risingapp.likeit.entity.Photo;
import com.risingapp.likeit.entity.User;
import com.risingapp.likeit.execption.NotEnoughChatRoomsException;
import com.risingapp.likeit.execption.NotEnoughMessagesException;
import com.risingapp.likeit.execption.SessionTimeOutException;
import com.risingapp.likeit.model.common.MessageResponse;
import com.risingapp.likeit.model.request.AddChatRoomRequest;
import com.risingapp.likeit.model.response.*;
import com.risingapp.likeit.repository.ChatRoomRepository;
import com.risingapp.likeit.repository.MessageRepository;
import com.risingapp.likeit.repository.PhotoRepository;
import com.risingapp.likeit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Service
public class ChatRoomService extends ParentService {

    @Autowired private ChatRoomRepository chatRoomRepository;
    @Autowired private MessageRepository messageRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private PhotoRepository photoRepository;

    @Autowired private ChatUtilResponseConverter chatUtilResponseConverter;

    public MessageResponse getChats(Long chatId, int count) throws SessionTimeOutException, NotEnoughChatRoomsException {
        User user = getSessionUser();
        List<ChatRoom> allRooms = chatRoomRepository.findAll();
        Collections.reverse(allRooms);
        List<ChatRoom> userRooms = new ArrayList<>();
        for (ChatRoom chatRoom : allRooms) {
            if (chatRoom.getUsers() == null) continue;
            if (chatRoom.getUsers().contains(user)) {
                userRooms.add(chatRoom);
                continue;
            }
        }
        GetChatRoomsResponse data = new GetChatRoomsResponse();
        int offSet;
        if (chatId == null) {
            offSet = 0;
        } else {
            offSet = userRooms.indexOf(chatRoomRepository.findOne(chatId));
        }
        if (offSet < 0) throw new NotEnoughChatRoomsException();
        if (offSet + count >= userRooms.size()) {
            data.setLast(true);
            userRooms = userRooms.subList(offSet, userRooms.size() - 1);
        } else {
            data.setLast(false);
            userRooms = userRooms.subList(offSet, offSet + count);
        }

        List<ChatRoomResponse> chatRoomResponses = chatUtilResponseConverter.buildChatRooms(userRooms);
        data.setRooms(chatRoomResponses);
        return new MessageResponse<>(data);
    }

    public MessageResponse getMessages(Long chatId, Long messageId, int count) throws SessionTimeOutException, NotEnoughMessagesException {
        User user = getSessionUser();
        ChatRoom chatRoom = chatRoomRepository.findOne(chatId);
        if (!user.getChatRooms().contains(chatRoom)) return new MessageResponse();
        List<Message> messages = chatRoom.getMessages();
        Collections.reverse(messages);
        GetChatRoomMessagesResponse data = new GetChatRoomMessagesResponse();
        int offSet;
        if (messageId == null) {
            offSet = 0;
        } else {
            offSet = messages.indexOf(messageRepository.findOne(messageId));
        }
        if (offSet < 0) throw new NotEnoughMessagesException();
        if (offSet + count >= messages.size()) {
            data.setLast(true);
            messages = messages.subList(offSet, messages.size() - 1);
        } else {
            data.setLast(false);
            messages = messages.subList(offSet, offSet + count);
        }
        List<ChatRoomMessageResponse> messageResponses = chatUtilResponseConverter.buildChatMessages(user, messages);
        data.setMessages(messageResponses);
        return  new MessageResponse<>(data);
    }

    public MessageResponse addChatRoom(AddChatRoomRequest request) {
        AddChatRoomResponse data = new AddChatRoomResponse();
        data.setUserIds(new ArrayList<>());
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(request.getName());
        for (Long userId : request.getUserIds()) {
            User user = userRepository.findOne(userId);
            chatRoom.getUsers().add(user);
            data.getUserIds().add(userId);
        }
        if (request.getPhotoId() != null) {
            Photo photo = photoRepository.findOne(request.getPhotoId());
            chatRoom.setPhoto(photo);
            data.setPhotoUrl(data.getPhotoUrl());
        }
        chatRoomRepository.save(chatRoom);
        data.setRoomId(chatRoom.getId());
        return new MessageResponse(data);
    }
}

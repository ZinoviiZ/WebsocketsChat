package com.risingapp.likeit.util.mock.generators;

import com.risingapp.likeit.entity.ChatRoom;
import com.risingapp.likeit.entity.User;
import com.risingapp.likeit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by oleg on 08.04.17.
 */
@Component
public class ChatGenerator extends Generator<ChatRoom> {
    @Autowired private UserRepository userRepository;
    @Autowired private MessageGenerator messageGenerator;
    @Autowired private WordGenerator wordGenerator;
    private Random random = new Random();
    private List<User> users;
    private int membersCount = 0;


    public void setMembersCount(int membersCount) {
        users = userRepository.findAll();
        this.membersCount = membersCount;
    }

    @Override
    public ChatRoom generateObject() {
        ChatRoom chatRoom = new ChatRoom();
        Set<Integer> chatUsersId = new HashSet<>();
        List<User> chatUsers = new ArrayList<>();
        int usersCount = users.size();
        while (chatUsers.size() <= membersCount) {
            chatUsersId.add(random.nextInt(usersCount));
        }
        for (Integer i: chatUsersId) {
            chatUsers.add(users.get(i));
        }
        messageGenerator.setUserList(chatUsers);
        chatRoom.setUsers(chatUsers);
        chatRoom.setName(wordGenerator.generateObject());
        chatRoom.setMessages(messageGenerator.generateObjects(20));
        return chatRoom;
    }

    @Override
    public List<ChatRoom> generateObjects(int count) {
        List<ChatRoom> chatRoomList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            chatRoomList.add(generateObject());
        }
        return chatRoomList;
    }

}

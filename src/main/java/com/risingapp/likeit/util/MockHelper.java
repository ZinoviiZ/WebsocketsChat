package com.risingapp.likeit.util;

import com.risingapp.likeit.entity.ChatRoom;
import com.risingapp.likeit.entity.Message;
import com.risingapp.likeit.entity.MessageLike;
import com.risingapp.likeit.entity.User;
import com.risingapp.likeit.repository.ChatRoomRepository;
import com.risingapp.likeit.repository.MessageLikeRepository;
import com.risingapp.likeit.repository.UserRepository;
import com.risingapp.likeit.util.mock.generators.ChatGenerator;
import com.risingapp.likeit.util.mock.generators.UserGenerator;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
@Log4j
@Component
public class MockHelper {

    @Autowired private UserRepository userRepository;
    @Autowired private ChatRoomRepository chatRoomRepository;
    @Autowired private UserGenerator userGenerator;
    @Autowired private ChatGenerator chatGenerator;
    @Autowired private MessageLikeRepository messageLikeRepository;


    @PostConstruct
    private void init() {
        List<User> users = userGenerator.generateObjects(10);
        User user = users.get(0);
        user.setEmail("admin");
        user.setPassword("password");
        userRepository.save(users);

        chatGenerator.setMembersCount(2);
        List<ChatRoom> chatRooms = chatGenerator.generateObjects(20);
        chatRoomRepository.save(chatRooms);
        mockLikes(chatRooms);
    }

    private void mockLikes(List<ChatRoom> chatRooms) {
        for (ChatRoom chatRoom : chatRooms) {
            List<User> roomUsers = chatRoom.getUsers();
            Collections.shuffle(roomUsers);
            for (Message message : chatRoom.getMessages()) {
                List<User> userLiked = chatRoom.getUsers().subList(0, new Random().nextInt(chatRoom.getUsers().size()));
                for (User user : userLiked) {
                    MessageLike messageLike = new MessageLike();
                    messageLike.setUser(user);
                    messageLike.setMessage(message);
                    messageLikeRepository.save(messageLike);
                }

            }
        }
    }
}

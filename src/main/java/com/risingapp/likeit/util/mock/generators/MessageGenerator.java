package com.risingapp.likeit.util.mock.generators;

import com.risingapp.likeit.entity.Message;
import com.risingapp.likeit.entity.User;
import com.risingapp.likeit.repository.UserRepository;
import com.risingapp.likeit.util.mock.generators.models.RandomMessage;
import com.risingapp.likeit.util.mock.generators.models.responses.GetRandomMessageResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by oleg on 07.04.17.
 */
@Log4j
public class MessageGenerator extends Generator<Message>{

    @Autowired
    private UserRepository userRepository;

    private RestTemplate template = new RestTemplate();
    private boolean isRandom = false;
    private List<User> users;
    private Random random = new Random();
    private int userCount = 1;

    public MessageGenerator(boolean isRandom, int userCount) {
        this.isRandom = isRandom;
        this.userCount = userCount;
        users = userRepository.findAll();
    }

    public MessageGenerator(List<User> users) {
        this.users = users;
        this.userCount = users.size();
    }

    public MessageGenerator(long userId) throws IllegalArgumentException {
        users = new ArrayList<>();
        User user = userRepository.findOne(userId);
        if (user != null) {
            users.add(user);
        } else {
            throw new IllegalArgumentException("Illegal user id!");
        }
    }

    @Override
    public Message generateObject() {
        String url = "http://api.icndb.com/jokes/random/1";
        User author = getUser();

        GetRandomMessageResponse responseList = requestMessages(author, url);
        if (!responseList.getType().equals(GetRandomMessageResponse.SUCCESS)) {
            return null;
        }
        return randomMessage(responseList.getValue().get(0), author);
    }

    @Override
    public List<Message> generateObjects(int count) {
        List<Message> messages = new ArrayList<>();
        User author;
        int currentCount = count;
        int currentMessageCount;
        int mod = count % userCount;
        String baseUrl = "http://api.icndb.com/jokes/random/";
        String currentUrl;
        for (int i = 0; i < userCount; i++) {
            currentMessageCount = count / userCount;
            if (currentMessageCount == 0) {
                currentMessageCount = 1;
            }
            currentCount -= currentMessageCount;
            if (currentCount < 0) {
                currentMessageCount += mod;
            }
            author = getUser();
            currentUrl = baseUrl + currentMessageCount;
            GetRandomMessageResponse responseList = requestMessages(author, currentUrl);
            if (!responseList.getType().equals(GetRandomMessageResponse.SUCCESS)) {
                continue;
            }
            for (RandomMessage message: responseList.getValue()) {
                messages.add(randomMessage(message, author));
            }
        }
        if (messages.isEmpty()) {
            return null;
        } else {
            return messages;
        }
    }

    private Message randomMessage(RandomMessage randomMessage, User author) {

        Message message = new Message();
        message.setText(randomMessage.getJoke());
        message.setUser(author);
        return message;
    }

    private GetRandomMessageResponse requestMessages(User author, String url) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("limitTo", "[nerdy]")
                .queryParam("firstName", author.getFirstName())
                .queryParam("lastName", author.getLastName());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<GetRandomMessageResponse> response = template.exchange(builder.build().toUriString(),
                HttpMethod.GET, entity, GetRandomMessageResponse.class);
        return response.getBody();
    }

    private User getUser() {
        if (!isRandom) {
            return users.get(0);
        } else {
            return users.get(random.nextInt(users.size()));
        }
    }

}

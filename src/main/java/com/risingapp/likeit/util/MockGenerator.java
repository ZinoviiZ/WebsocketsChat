package com.risingapp.likeit.util;

import com.risingapp.likeit.entity.Photo;
import com.risingapp.likeit.entity.User;
import com.risingapp.likeit.enums.UserRole;
import com.risingapp.likeit.repository.PhotoRepository;
import com.risingapp.likeit.repository.UserRepository;
import com.risingapp.likeit.util.mock.GetRandomUsersResponse;
import com.risingapp.likeit.util.mock.RandomUserResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
@Log4j
@Component
public class MockGenerator {

    @Autowired private UserRepository userRepository;
    @Autowired private PhotoRepository photoRepository;


    public <T extends User> List<T> requestRandomUsers(int size, Class<T> userClass) {
        RestTemplate template = new RestTemplate();
        GetRandomUsersResponse responseList = template.getForObject(String.format("https://randomuser.me/api/?results=%d", size), GetRandomUsersResponse.class);
        List<T> users = new ArrayList<>();
        log.info(responseList.getResults());
        for (RandomUserResponse response : responseList.getResults()) {
            T random = randomUser(response, userClass);
            if (random != null) {
                users.add(random);
            }

        }
        return users;
    }

    public <T extends User> T requestRandomUser(Class<T> userClass) {
        RestTemplate template = new RestTemplate();
        GetRandomUsersResponse responseList = template.getForObject("https://randomuser.me/api/", GetRandomUsersResponse.class);
        return randomUser(responseList.getResults().get(0), userClass);
    }
    public <T extends User> T randomUser(RandomUserResponse response, Class<T> userClass) {
        User user = null;
        try {
            user = userClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if (user != null) {
            String firstName = response.getName().getFirst();
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
            user.setFirstName(firstName);
            user.setLastName(response.getName().getLast());
            user.setEmail(response.getEmail());
            user.setBirthday(response.getDob());
            user.setPassword(response.getLogin().getPassword());
            user.setPhotoUrl(response.getPicture().getMedium());
            return (T) user;
        }
        return null;
    }

    @Transactional
    @PostConstruct
    private void init() {
        User user = new User()
        user.setEmail("admin");
        user.setPassword("password");
        user.setUserRole(UserRole.ADMIN);
        user.setRegistrationDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        userRepository.save(user);

        Photo photo = new Photo();
        photo.setBase64("adsads");
        photoRepository.save(photo);
        user.setPhoto(photo);
        userRepository.save(user);
    }
}

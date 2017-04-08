package com.risingapp.likeit.util.mock.generators;

import com.risingapp.likeit.entity.Photo;
import com.risingapp.likeit.entity.User;
import com.risingapp.likeit.repository.PhotoRepository;
import com.risingapp.likeit.util.mock.generators.models.responses.GetRandomUsersResponse;
import com.risingapp.likeit.util.mock.generators.models.RandomUserResponse;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.extern.log4j.Log4j;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import sun.nio.ch.IOUtil;

import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by oleg on 07.04.17.
 */
@Log4j
@PropertySource("classpath:url.properties")
public class UserGenerator extends Generator<User>{

    @Autowired
    PhotoRepository photoRepository;
    @Value("${photo.url}")
    private static String PHOTO_URL;

    private RestTemplate template = new RestTemplate();

    @Override
    public User generateObject() {
        try {
            template = new RestTemplate();
            GetRandomUsersResponse responseList = template.getForObject("https://randomuser.me/api/", GetRandomUsersResponse.class);
            return randomUser(responseList.getResults().get(0));
        } catch (RestClientException e) {
            log.error("Network error", e);
            log.warn("Generate default user");
            return defaultUser(1);
        }

    }

    @Override
    public List<User> generateObjects(int count) {
        List<User> users = new ArrayList<>();
        try {

            GetRandomUsersResponse responseList = template.getForObject(String.format("https://randomuser.me/api/?results=%d", count), GetRandomUsersResponse.class);

            for (RandomUserResponse response : responseList.getResults()) {
                users.add(randomUser(response));
            }
        } catch (RestClientException e) {
            log.error("Network error", e);
            log.warn("Generate default users");
            for (int i = 0; i < count; i++) {
                users.add(defaultUser(i));
            }
        }
        return users;
    }


    private User randomUser(RandomUserResponse response) {
        User user = new User();
        String firstName = response.getName().getFirst();
        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        user.setFirstName(firstName);
        user.setLastName(response.getName().getLast());
        user.setEmail(response.getEmail());
        user.setBirthday(response.getDob());
        user.setPassword(response.getLogin().getPassword());
        Photo photo = getPhoto(response.getPicture().getThumbnail());
        if (photo != null) {
            user.setPhoto(photo);
            user.setPhotoUrl(photo.getPhotoUrl());
        }
        return user;
    }

    private User defaultUser(int id) {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setEmail(user.getFirstName().toLowerCase() + id + "@gmail.com");
        return user;
    }

    @Transactional
    private Photo getPhoto(String photoUrl) {
        Photo photo = null;
        try {
            InputStream inputStream = new FileInputStream(photoUrl);
            photo = new Photo();
            String base64 = Base64.encode(IOUtils.toByteArray(inputStream));
            photo.setBase64(base64);
            photoRepository.save(photo);
            photo.setPhotoUrl(PHOTO_URL + photo.getId());
            return photo;
        } catch (FileNotFoundException e) {
            log.error("InputStream error", e);
        } catch (IOException e) {
            log.error("Bytes read error", e);
        }
        return photo;
    }
}

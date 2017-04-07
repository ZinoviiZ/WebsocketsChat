package com.risingapp.likeit.util;

import com.risingapp.likeit.entity.Photo;
import com.risingapp.likeit.entity.User;
import com.risingapp.likeit.enums.UserRole;
import com.risingapp.likeit.repository.PhotoRepository;
import com.risingapp.likeit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
@Component
public class MockGenerator {

    @Autowired private UserRepository userRepository;
    @Autowired private PhotoRepository photoRepository;

    @Transactional
    @PostConstruct
    private void init() {
        User user = new User();
        user.setEmail("admin");
        user.setPassword("password");
        user.setUserRole(UserRole.ADMIN);
        user.setRegistrationDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        userRepository.save(user);

        Photo photo = new Photo();
        photo.setBase64("adsads");
        photoRepository.save(photo);
        user.setPhoto(photo);
    }
}

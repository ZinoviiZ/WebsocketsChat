package com.risingapp.likeit.util;

import com.risingapp.likeit.entity.User;
import com.risingapp.likeit.repository.PhotoRepository;
import com.risingapp.likeit.repository.UserRepository;
import com.risingapp.likeit.util.mock.generators.UserGenerator;
import com.risingapp.likeit.util.mock.generators.models.responses.GetRandomUsersResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
@Log4j
@Component
public class MockHelper {

    @Autowired private UserRepository userRepository;
    @Autowired private UserGenerator userGenerator;

    @PostConstruct
    private void init() {
        List<User> users = userGenerator.generateObjects(10);
        User user = users.get(0);
        user.setEmail("admin");
        user.setPassword("password");
        userRepository.save(users);
    }
}

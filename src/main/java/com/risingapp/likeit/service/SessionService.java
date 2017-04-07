package com.risingapp.likeit.service;

import com.risingapp.likeit.entity.User;
import com.risingapp.likeit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
@Service
public class SessionService {

    @Autowired private UserRepository userRepository;

    public User getCurrentUser() {
        return null;
    }
}

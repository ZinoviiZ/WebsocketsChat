package com.risingapp.likeit.service;

import com.risingapp.likeit.convertor.response.ErrorResponseConverter;
import com.risingapp.likeit.entity.User;
import com.risingapp.likeit.execption.SessionTimeOutException;
import com.risingapp.likeit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
public abstract class ParentService {

    @Autowired protected ErrorResponseConverter errorResponseConverter;
    @Autowired private UserRepository userRepository;

    protected User getSessionUser() throws SessionTimeOutException {
        org.springframework.security.core.userdetails.User sessionUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(sessionUser.getUsername());
        if (currentUser == null) {
            throw new SessionTimeOutException();
        }
        return currentUser;
    }
}
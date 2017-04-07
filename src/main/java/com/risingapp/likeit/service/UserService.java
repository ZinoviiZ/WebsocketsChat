package com.risingapp.likeit.service;

import com.risingapp.likeit.convertor.request.UserRequestConvertor;
import com.risingapp.likeit.convertor.response.UserResponseConvertor;
import com.risingapp.likeit.entity.User;
import com.risingapp.likeit.execption.UserWithThisEmailExists;
import com.risingapp.likeit.model.common.MessageResponse;
import com.risingapp.likeit.model.request.UserRequest;
import com.risingapp.likeit.model.response.UserResponse;
import com.risingapp.likeit.repository.PhotoRepository;
import com.risingapp.likeit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zinoviyzubko on 05.04.17.
 */
@Service
public class UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private PhotoRepository photoRepository;

    @Autowired private SessionService sessionService;

    @Autowired private UserRequestConvertor userRequestConvertor;
    @Autowired private UserResponseConvertor userResponseConvertor;

    @Transactional
    public MessageResponse getUserById(Long id) {
        User user = userRepository.findOne(id);
        UserResponse data = userResponseConvertor.convert(user);
        MessageResponse<UserResponse> response = new MessageResponse<>(data);
        return response;
    }

    @Transactional
    public MessageResponse getCurrentUser() {
        User user = sessionService.getCurrentUser();
        UserResponse data = userResponseConvertor.convert(user);
        MessageResponse<UserResponse> response = new MessageResponse<>(data);
        return response;
    }

    @Transactional
    public MessageResponse saveUser(UserRequest request) throws UserWithThisEmailExists {
        User user = userRepository.findByEmail(request.getEmail());
        if (user != null) throw new UserWithThisEmailExists();
        user = userRequestConvertor.convert(request);
        userRepository.save(user);
        return new MessageResponse();
    }

    @Transactional
    public MessageResponse updateUser(Long userId, UserRequest request) {
        User user = userRepository.findOne(userId);
        if (request.getEmail() != null) user.setEmail(request.getEmail());
        if (request.getPassword() != null) user.setPassword(request.getPassword());
        if (request.getFirstName() != null) user.setFirstName(request.getFirstName());
        if (request.getLastName() != null) user.setLastName(request.getLastName());
        if (request.getBirthday() != null) user.setBirthday(request.getBirthday());
        userRepository.save(user);
        return new MessageResponse();
    }
}

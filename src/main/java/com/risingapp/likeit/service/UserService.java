package com.risingapp.likeit.service;

import com.risingapp.likeit.convertor.request.UserRequestConverter;
import com.risingapp.likeit.convertor.response.UserUtilResponseConverter;
import com.risingapp.likeit.entity.*;
import com.risingapp.likeit.execption.SessionTimeOutException;
import com.risingapp.likeit.execption.UserWithThisEmailExists;
import com.risingapp.likeit.model.common.MessageResponse;
import com.risingapp.likeit.model.request.ChangeProfileRequest;
import com.risingapp.likeit.model.request.ChangeSettingRequest;
import com.risingapp.likeit.model.request.UserRequest;
import com.risingapp.likeit.model.response.*;
import com.risingapp.likeit.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by zinoviyzubko on 05.04.17.
 */
@Service
public class UserService extends ParentService {

    @Autowired private UserRepository userRepository;
    @Autowired private PhotoRepository photoRepository;
    @Autowired private BluetoothDataRepository bluetoothDataRepository;
    @Autowired private NetworkDataRepository networkDataRepository;
    @Autowired private UserSettingsRepository userSettingsRepository;

    @Autowired private UserRequestConverter userRequestConverter;
    @Autowired private UserUtilResponseConverter userUtilResponseConverter;

    @Transactional
    public MessageResponse getUserById(Long id) {
        User user = userRepository.findOne(id);
        UserResponse data = userUtilResponseConverter.buildUserResponse(user);
        MessageResponse<UserResponse> response = new MessageResponse<>(data);
        return response;
    }

    @Transactional
    public MessageResponse getCurrentUser() throws SessionTimeOutException {
        User user = getSessionUser();
        UserResponse data = userUtilResponseConverter.buildUserResponse(user);
        MessageResponse<UserResponse> response = new MessageResponse<>(data);
        return response;
    }

    @Transactional
    public MessageResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        UserResponse data = userUtilResponseConverter.buildUserResponse(user);
        return new MessageResponse<UserResponse>(data);
    }

    @Transactional
    public MessageResponse saveUser(UserRequest request) throws UserWithThisEmailExists {
        User user = userRepository.findByEmail(request.getEmail());
        if (user != null) throw new UserWithThisEmailExists();
        user = userRequestConverter.convert(request);
        //TODO
        BluetoothData bluetoothData = new BluetoothData();
        bluetoothDataRepository.save(bluetoothData);
        user.setBluetoothData(bluetoothData);
        NetworkData networkData = new NetworkData();
        networkDataRepository.save(networkData);
        user.setNetworkData(networkData);
        UserSetting userSetting = new UserSetting();
        userSettingsRepository.save(userSetting);
        user.setSetting(userSetting);
        userRepository.save(user);
        return new MessageResponse();
    }

    @Transactional
    public MessageResponse updateUser(Long userId, UserRequest request) {
        User user = userRepository.findOne(userId);
        if (request.getEmail() != null)
            user.setEmail(request.getEmail());
        if (request.getPassword() != null)
            user.setPassword(request.getPassword());
        if (request.getFirstName() != null)
            user.setFirstName(request.getFirstName());
        if (request.getLastName() != null)
            user.setLastName(request.getLastName());
        if (request.getBirthday() != null)
            user.setBirthday(request.getBirthday());
        userRepository.save(user);
        return new MessageResponse();
    }

    @Transactional
    public MessageResponse getMyProfile() throws SessionTimeOutException {
        User user = getSessionUser();
        Photo photo = user.getPhoto();
        GetMyProfileResponse data = userUtilResponseConverter.buildMyProfile(user, photo);
        return new MessageResponse<GetMyProfileResponse>(data);
    }

    @Transactional
    public MessageResponse getProfile(long userId) throws SessionTimeOutException {
        User user = getSessionUser();
        Photo photo = user.getPhoto();
        UserSetting setting = user.getSetting();
        GetProfileResponse data = userUtilResponseConverter.buildProfile(user, setting, photo);
        return new MessageResponse<GetProfileResponse>(data);
    }

    @Transactional
    public MessageResponse updateProfile(ChangeProfileRequest request) throws SessionTimeOutException {
        User user = getSessionUser();
        Photo photo = user.getPhoto();
        if (request.getEmail() != null)
            user.setEmail(request.getEmail());
        if (request.getPassword() != null)
            user.setPassword(request.getPassword());
        if (request.getFirstName() != null)
            user.setFirstName(request.getFirstName());
        if (request.getLastName() != null)
            user.setLastName(request.getLastName());
        if (request.getBirthday() != null)
            user.setBirthday(request.getBirthday());
        if (request.getPhotoId() != null) {
            photo = photoRepository.findOne(request.getPhotoId());
            user.setPhoto(photo);
        }
        if (request.getPhoneNumber() != null) {
            user.setPhoneNumber(request.getPhoneNumber());
        }
        userRepository.save(user);
        photoRepository.save(photo);
        ChangeProfileResponse data = userUtilResponseConverter.buildProfileAfterUpdate(user, photo);
        return new MessageResponse<ChangeProfileResponse>(data);
    }

    @Transactional
    public MessageResponse updateSettings(ChangeSettingRequest request) throws SessionTimeOutException {
        User user = getSessionUser();
        UserSetting setting = user.getSetting();
        if (request.getShowEmail() != null)
            setting.setShowEmail(request.getShowEmail());
        if (request.getShowPhoneNumber() != null)
            setting.setShowPhoneNumber(request.getShowPhoneNumber());
        if (request.getShowBirthday() != null)
            setting.setShowBirthday(request.getShowBirthday());
        user.setSetting(setting);
        userRepository.save(user);
        ChangeSettingResponse data = userUtilResponseConverter.buildSettingResponse(setting);
        return new MessageResponse<ChangeSettingResponse>(data);
    }
}

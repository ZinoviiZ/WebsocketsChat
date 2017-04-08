package com.risingapp.likeit.convertor.response;

import com.risingapp.likeit.entity.Photo;
import com.risingapp.likeit.entity.User;
import com.risingapp.likeit.entity.UserSetting;
import com.risingapp.likeit.model.response.*;
import org.springframework.stereotype.Component;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Component
public class UserUtilResponseConverter {

    public UserResponse buildUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setRegistrationDate(user.getRegistrationDate());
        response.setBirthday(user.getBirthday());
        return response;
    }

    public GetMyProfileResponse buildMyProfile(User user, Photo photo) {
        GetMyProfileResponse profileResponse = new GetMyProfileResponse();
        profileResponse.setEmail(user.getEmail());
        profileResponse.setFirstName(user.getFirstName());
        profileResponse.setLastName(user.getLastName());
        profileResponse.setBirthday(user.getBirthday());
        profileResponse.setPhoneNumber(user.getPhoneNumber());
        if (photo != null)
            profileResponse.setPhotoUrl(photo.getPhotoUrl());
        return profileResponse;
    }

    public GetProfileResponse buildProfile(User user, UserSetting setting, Photo photo) {
        GetProfileResponse profileResponse = new GetProfileResponse();
        if (setting.getShowEmail())
            profileResponse.setEmail(user.getEmail());
        if (setting.getShowPhoneNumber())
            profileResponse.setPhoneNumber(user.getPhoneNumber());
        if (setting.getShowBirthday())
            profileResponse.setBirthday(user.getBirthday());
        profileResponse.setFirstName(user.getFirstName());
        profileResponse.setLastName(user.getLastName());
        profileResponse.setLastVisit(user.getLastVisit());
        if (photo != null)
            profileResponse.setPhotoUrl(photo.getPhotoUrl());
        return profileResponse;
    }

    public ChangeProfileResponse buildProfileAfterUpdate(User user, Photo photo) {
        ChangeProfileResponse profileResponse = new ChangeProfileResponse();
        profileResponse.setEmail(user.getEmail());
        profileResponse.setPassword(user.getPassword());
        profileResponse.setFirstName(user.getFirstName());
        profileResponse.setLastName(user.getLastName());
        profileResponse.setBirthday(user.getBirthday());
        profileResponse.setPhoneNumber(user.getPhoneNumber());
        if (photo != null)
            profileResponse.setPhotoUrl(photo.getPhotoUrl());
        return profileResponse;
    }

    public ChangeSettingResponse buildSettingResponse(UserSetting setting) {
        ChangeSettingResponse settingResponse = new ChangeSettingResponse();
        settingResponse.setShowEmail(setting.getShowEmail());
        settingResponse.setShowPhoneNumber(setting.getShowPhoneNumber());
        settingResponse.setShowBirthday(setting.getShowBirthday());
        return settingResponse;
    }
}

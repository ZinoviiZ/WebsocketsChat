package com.risingapp.likeit.util.mock.generators;

import com.risingapp.likeit.ProjectEnviroment;
import com.risingapp.likeit.entity.*;
import com.risingapp.likeit.enums.UserRole;
import com.risingapp.likeit.repository.BluetoothDataRepository;
import com.risingapp.likeit.repository.NetworkDataRepository;
import com.risingapp.likeit.repository.PhotoRepository;
import com.risingapp.likeit.repository.UserSettingsRepository;
import com.risingapp.likeit.util.mock.generators.models.RandomUserResponse;
import com.risingapp.likeit.util.mock.generators.models.responses.GetRandomUsersResponse;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.extern.log4j.Log4j;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by oleg on 07.04.17.
 */
@Log4j
@Component
public class UserGenerator extends Generator<User>{

    @Autowired private PhotoRepository photoRepository;
    @Autowired private BluetoothDataRepository bluetoothDataRepository;
    @Autowired private NetworkDataRepository networkDataRepository;
    @Autowired private UserSettingsRepository userSettingsRepository;


    private RestTemplate template = new RestTemplate();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);


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
        log.info("Generate users: " + count);
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
        String lastName = response.getName().getLast();
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(response.getEmail());
        try {
            Date date = dateFormat.parse(response.getDob());
            user.setBirthday(date.getTime());
        } catch (ParseException e) {
            log.error("Parse data error", e);
        }
        user.setPassword(response.getLogin().getPassword());
        user.setUserRole(UserRole.ADMIN);
        BluetoothData bluetoothData = new BluetoothData();
        NetworkData networkData = new NetworkData();
        UserSetting userSetting = new UserSetting();

        bluetoothDataRepository.save(bluetoothData);
        networkDataRepository.save(networkData);
        userSettingsRepository.save(userSetting);

        user.setBluetoothData(bluetoothData);
        user.setNetworkData(networkData);
        user.setSetting(userSetting);

        Photo photo = getPhoto(response.getPicture().getMedium());
        if (photo != null) {
            user.setPhoto(photo);
        }
        return user;
    }

    private User defaultUser(int id) {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setUserRole(UserRole.ADMIN);
        user.setEmail(user.getFirstName().toLowerCase() + id + "@gmail.com");
        return user;
    }

    private Photo getPhoto(String photoUrl) {
        Photo photo = null;
        try {
            URL url = new URL(photoUrl);
            photo = new Photo();
            String base64 = Base64.encode(IOUtils.toByteArray(url.openStream()));
            photo.setBase64(base64);
            photoRepository.save(photo);
            photo.setPhotoUrl(ProjectEnviroment.ENVIRONMENT_URL + photo.getId());
            photoRepository.save(photo);
            return photo;
        } catch (FileNotFoundException e) {
            log.error("InputStream error", e);
        } catch (IOException e) {
            log.error("Bytes read error", e);
        }
        return photo;
    }
}

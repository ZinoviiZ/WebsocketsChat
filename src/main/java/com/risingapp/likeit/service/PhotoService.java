package com.risingapp.likeit.service;

import com.risingapp.likeit.entity.Photo;
import com.risingapp.likeit.entity.User;
import com.risingapp.likeit.model.common.MessageResponse;
import com.risingapp.likeit.repository.PhotoRepository;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zinoviyzubko on 05.04.17.
 */
@Service
@PropertySource("classpath:url.properties")
public class PhotoService {

    @Value("${photo.url}")
    private static String PHOTO_URL;

    @Autowired private PhotoRepository photoRepository;

    @Autowired private SessionService sessionService;

    @Transactional
    public ResponseEntity<Void> getPhoto(Long photoId, HttpServletResponse response) throws IOException {
        Photo photo = photoRepository.findOne(photoId);
        byte[] bytes = Base64.decode(photo.getBase64());
        response.setContentType("image/png");
        response.getOutputStream().write(bytes);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    public MessageResponse savePhoto(MultipartFile file) throws IOException {
        User currentUser = sessionService.getCurrentUser();
        String base64 = Base64.encode(file.getBytes());
        Photo photo = new Photo();
        photo.setBase64(base64);
        photoRepository.save(photo);
        String photoUrl = PHOTO_URL + currentUser.getId();
        photo.setLink(photoUrl);
        photoRepository.save(photo);
        currentUser.setPhoto(photo);
        return new MessageResponse();
    }

    @Transactional
    public MessageResponse removePhoto(Long photoId) {
        photoRepository.delete(photoId);
        return new MessageResponse();
    }
}

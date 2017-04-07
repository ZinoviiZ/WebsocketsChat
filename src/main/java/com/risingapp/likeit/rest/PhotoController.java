package com.risingapp.likeit.rest;

import com.risingapp.likeit.model.common.MessageResponse;
import com.risingapp.likeit.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zinoviyzubko on 05.04.17.
 */
@Controller
@RequestMapping(value = "/rest/photos")
public class PhotoController {

    @Autowired private PhotoService photoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getPhoto(@PathVariable("id") Long photoId, HttpServletResponse response) throws IOException {
        return photoService.getPhoto(photoId, response);
    }

    @RequestMapping(method = RequestMethod.POST)
    public MessageResponse addPhoto(@RequestParam(value = "photo") MultipartFile file) throws IOException {
        return photoService.savePhoto(file);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public MessageResponse removePhoto(@PathVariable("id") Long photoId) {
        return photoService.removePhoto(photoId);
    }
}

package com.risingapp.likeit.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zinoviyzubko on 05.04.17.
 */
@Controller
@RequestMapping(value = "/rest/photo")
public class PhotoController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getPhoto(@PathVariable("id") Long photoId) {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addPhoto(@RequestParam(value = "photo")MultipartFile file) {
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity removePhoto(@RequestParam(value = "id") Long photoId) {
        return null;
    }
}

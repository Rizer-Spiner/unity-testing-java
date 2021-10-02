package com.endregas.warriors.unitytesting.controllers;

import com.endregas.warriors.unitytesting.enums.VideoSavingEnum;
import com.endregas.warriors.unitytesting.services.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class VideoController {

    final VideoService videoService;

    @PostMapping(value = "/video", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveVideo(@RequestBody MultipartFile file) {
        VideoSavingEnum videoSavingEnum = videoService.saveVideo(file);
        if(VideoSavingEnum.SAVED.equals(videoSavingEnum)){
            return new ResponseEntity<>("File is uploaded succesfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

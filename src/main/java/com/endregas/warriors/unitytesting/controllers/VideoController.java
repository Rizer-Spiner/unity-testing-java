package com.endregas.warriors.unitytesting.controllers;

import com.endregas.warriors.unitytesting.exceptions.NoVideosException;
import com.endregas.warriors.unitytesting.exceptions.VideoNotFoundException;
import com.endregas.warriors.unitytesting.services.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class VideoController {

    final VideoService videoService;

    @PostMapping(value = "/video", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveVideo(@RequestBody MultipartFile file) throws IOException {
        videoService.saveVideo(file);
        return ResponseEntity.ok().body("File is uploaded succesfully");

    }

    @GetMapping(value = "/video/recent")
    public ResponseEntity<String> getMostRecentVideo() throws NoVideosException, VideoNotFoundException {
        return ResponseEntity.ok().body(videoService.findMostRecentVideo());
    }

}

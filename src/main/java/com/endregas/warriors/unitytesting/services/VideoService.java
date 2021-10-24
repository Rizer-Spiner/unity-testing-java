package com.endregas.warriors.unitytesting.services;

import com.endregas.warriors.unitytesting.enums.VideoSavingEnum;
import com.endregas.warriors.unitytesting.exceptions.NoVideosException;
import com.endregas.warriors.unitytesting.exceptions.VideoNotFoundException;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

    VideoSavingEnum saveVideo(MultipartFile file);

    String findMostRecentVideo() throws NoVideosException, VideoNotFoundException;

}

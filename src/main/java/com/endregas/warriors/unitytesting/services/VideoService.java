package com.endregas.warriors.unitytesting.services;

import com.endregas.warriors.unitytesting.enums.VideoSavingEnum;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

    VideoSavingEnum saveVideo(MultipartFile file);

}

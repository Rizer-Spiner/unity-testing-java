package com.endregas.warriors.unitytesting.services;

import com.endregas.warriors.unitytesting.enums.VideoSavingEnum;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class VideoServiceImpl implements VideoService {

    public static final String VIDEO_DIRECTORY = "../videos/";

    @Override
    public VideoSavingEnum saveVideo(MultipartFile file) {
        try {
            createDirectoryIfDoesntExist();
            saveFile(file);
            return VideoSavingEnum.SAVED;
        } catch (IOException e) {
            e.printStackTrace();
            return VideoSavingEnum.ERROR;
        }
    }

    private void saveFile(MultipartFile file) throws IOException {
        File convertFile = new File(VIDEO_DIRECTORY + file.getOriginalFilename());
        if(!convertFile.createNewFile()){
            overrideVideo();
        }
        try(FileOutputStream fout = new FileOutputStream(convertFile)){
            fout.write(file.getBytes());
        }
    }

    private void createDirectoryIfDoesntExist() {
        File directory = new File(VIDEO_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private void overrideVideo(){
        //TODO: implement video overriding or video renaming
    }
}

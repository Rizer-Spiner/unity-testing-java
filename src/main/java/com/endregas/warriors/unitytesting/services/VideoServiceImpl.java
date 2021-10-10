package com.endregas.warriors.unitytesting.services;

import com.endregas.warriors.unitytesting.enums.VideoSavingEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
public class VideoServiceImpl implements VideoService {

    public static final String VIDEO_DIRECTORY = "src/main/resources/videos/";
    public static final int INITIAL_POSTFIX = 1;

    @Override
    public VideoSavingEnum saveVideo(MultipartFile file) {
        try {
            createDirectoryIfDoesntExist();
            saveFile(file);
            return VideoSavingEnum.SAVED;
        } catch (IOException e) {
            log.error(e.getMessage());
            return VideoSavingEnum.ERROR;
        }
    }

    private void saveFile(MultipartFile file) throws IOException {
        File convertFile = new File(VIDEO_DIRECTORY + file.getOriginalFilename());
        if (!convertFile.createNewFile()) {
            convertFile = addSuffix(convertFile, INITIAL_POSTFIX);
        }
        long startTime = System.nanoTime();
        try (InputStream in = file.getInputStream(); FileOutputStream out = new FileOutputStream(convertFile)) {
            byte[] buffer = new byte[4096]; //Buffer size, Usually 1024-4096
            int len;
            while ((len = in.read(buffer, 0, buffer.length)) > 0) {
                out.write(buffer, 0, len);
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        log.info(String.format("File %s (%d bytes) was uploaded in %d milliseconds", file.getName(), file.getSize(), duration));
    }

    private void createDirectoryIfDoesntExist() {
        File directory = new File(VIDEO_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private File addSuffix(File convertFile, int suffix) throws IOException {
        File renamedFile = new File(convertFile.toURI().getPath().replaceFirst(".mp4", "(" + suffix + ").mp4"));
        if (!renamedFile.createNewFile()) {
            return addSuffix(convertFile, suffix + 1);
        }
        return renamedFile;
    }
}

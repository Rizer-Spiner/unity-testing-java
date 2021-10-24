package com.endregas.warriors.unitytesting.services.impl;

import com.endregas.warriors.unitytesting.exceptions.NoVideosException;
import com.endregas.warriors.unitytesting.services.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class VideoServiceImplTest {

    private static final String VIDEO_DIRECTORY = "src/main/resources/videos/";
    private static final String TEST_VIDEO_DIRECTORY = "src/test/resources/videos/black screen.mp4";
    private static final int INITIAL_POSTFIX = 1;
    private static final String TEST_VIDEO_NAME = "black screen.mp4";
    private static final String THIRD_TEST_VIDEO_NAME = "black screen(2).mp4";

    VideoService videoService = new VideoServiceImpl();

    File videoDirectory;

    @BeforeEach
    void setup(){
        videoDirectory = new File(VIDEO_DIRECTORY);
        deleteVideoDirectory();
        videoDirectory.mkdirs();
    }

    private void deleteVideoDirectory() {
        File[] allFiles = videoDirectory.listFiles();
        if(allFiles != null){
            for(File file : allFiles){
                file.delete();
            }
        }
        videoDirectory.delete();
    }

    @Test
    void findLatest_directoryDoesNotExist() {
        videoDirectory.delete();
        assertFalse(videoDirectory.exists());
        assertThrows(NoVideosException.class, () -> videoService.findMostRecentVideo());
    }

    @Test
    void findLatest_noVideosInDirectory() {
        assertTrue(videoDirectory.exists());
        assertThrows(NoVideosException.class, () -> videoService.findMostRecentVideo());
    }

    @Test
    void findLatest_findFromOneVideo() throws IOException {
        MultipartFile testVideo = new MockMultipartFile("black screen.mp4", new FileInputStream(TEST_VIDEO_DIRECTORY));
        for(int i = 0; i < 1; i++){
            saveFile(testVideo);
        }
        assertEquals(TEST_VIDEO_NAME, videoService.findMostRecentVideo());
    }

    @Test
    void findLatest_findFromSeveralVideos() throws IOException {
        MultipartFile testVideo = new MockMultipartFile("black screen.mp4", new FileInputStream(TEST_VIDEO_DIRECTORY));
        for(int i = 0; i < 3; i++){
            saveFile(testVideo);
        }
        assertEquals(THIRD_TEST_VIDEO_NAME, videoService.findMostRecentVideo());
    }

    private void saveFile(MultipartFile file) throws IOException {
        File convertFile = new File(VIDEO_DIRECTORY + file.getName());
        if (!convertFile.createNewFile()) {
            convertFile = addSuffix(convertFile, INITIAL_POSTFIX);
        }
        try (InputStream in = file.getInputStream(); FileOutputStream out = new FileOutputStream(convertFile)) {
            byte[] buffer = new byte[4096]; //Buffer size, Usually 1024-4096
            int len;
            while ((len = in.read(buffer, 0, buffer.length)) > 0) {
                out.write(buffer, 0, len);
            }
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
package EduLink.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

    @Value("${file.upload-dir}")
    private String fileUploadDir;

    // 비디오 파일 서빙을 위한 메서드
    @GetMapping("/video/{classNum}/{videoFileName}")
    public ResponseEntity<byte[]> serveVideo(@PathVariable String classNum, @PathVariable String videoFileName) throws IOException {
        // 비디오 파일 경로 설정
        Path videoPath = Paths.get(fileUploadDir + "/video/" + classNum + "/" + videoFileName);
        byte[] videoBytes = Files.readAllBytes(videoPath);
        
        // 비디오 콘텐츠 유형 설정 (예: MP4 파일)
        MediaType mediaType = MediaType.valueOf("video/mp4");

        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + videoFileName + "\"")
                .body(videoBytes);
    }
}

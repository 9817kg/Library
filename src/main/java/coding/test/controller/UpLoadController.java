package coding.test.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UpLoadController {
    // 업로드 경로를 정적 리소스 디렉토리로 설정
    private String uploadPath = "src/main/resources/static/uploadImg";

    @PostMapping("/uploadajax")
    public void uploadFile(@RequestParam("upFiles") MultipartFile[] uploadFiles) {
	for (MultipartFile upFile : uploadFiles) {
	    String orgName = upFile.getOriginalFilename();

	    try {
		// 정적 리소스 디렉토리에 업로드한 파일 저장
		Path filePath = Paths.get(uploadPath, orgName);
		upFile.transferTo(filePath);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

}

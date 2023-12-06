package coding.test.controller;

import java.net.URLEncoder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadResultDTO {
    // 서버 폴더에 저장된 이미지의 경로 및 이름을 얻어내서 사용자에서 image src 의 값으로 지정될 경로를 리턴한다.
    
    private String fileName;
    private String uuid;
    private String folderPath;
    
    public String getImageURL() {
	    try {
	        String encodedFileName = URLEncoder.encode(uuid + "_" + fileName, "utf-8");
	        return folderPath + "/" + encodedFileName;
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	    return "";
	}
    
}

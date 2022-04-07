package wd.team4.everycare.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class UploadFile {

    private Long id;
    private String uploadFileName;
    private String storeFileName;

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

    public String upload(MultipartFile uploadFile) {
        String uploadFolder = "C:\\upload";

        String uploadFolderPath = getFolder();

        // make Folder ---
        File uploadPath = new File(uploadFolder, getFolder());
        log.info("upload path : " + uploadPath);

        if(uploadPath.exists() == false){
            uploadPath.mkdirs();
        }

        // make yyyy/MM/dd folder

        log.info("----------");
        log.info("Upload File Name : " + uploadFile.getOriginalFilename());
        log.info("Upload File Size : " + uploadFile.getSize());

        String uploadFileName = uploadFile.getOriginalFilename();

        // IE file path
        uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);

        // 띄어쓰기 제거
        uploadFileName = uploadFileName.replaceAll(" ", "");

        log.info("only file name : " + uploadFileName);


        return null;
    }

    private String getFolder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();
        String str = sdf.format(date);

        return str.replace("-", File.separator);
    }
}

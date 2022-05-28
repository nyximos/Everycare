package wd.team4.everycare.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class MultipartFileDTO {
    
    private MultipartFile attachFile;

}

package wd.team4.everycare.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class MultipartFileDTO {

    private List<MultipartFile> attachFiles;

}

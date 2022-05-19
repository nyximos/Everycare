package wd.team4.everycare.dto.careNote;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class CareNoteImageDTO {

    private MultipartFile attachFile;

}

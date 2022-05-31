package wd.team4.everycare.dto.careNote;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CareNoteImageDTO {

    private MultipartFile attachFile;

}

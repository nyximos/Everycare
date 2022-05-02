package wd.team4.everycare.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CertificationFormDTO {

    private String classification;
    private MultipartFile attachFile;
    private Long careSitterId;

}

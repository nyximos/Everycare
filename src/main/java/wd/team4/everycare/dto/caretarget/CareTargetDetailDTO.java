package wd.team4.everycare.dto.caretarget;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import wd.team4.everycare.domain.Gender;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.GenderDTO;
import wd.team4.everycare.dto.ImageDTO;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CareTargetDetailDTO {

    private Long id;
    private String name;
    private Gender gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
    private Long height;
    private Long weight;
    private String zipcode;
    private String address;
    private String detailedAddress;
    private int longTermCareGrade;
    private String comment;
    private int pet;
    private int isCctvAgreement;
    private String careType;
    private int coronaTest;
    private List<ImageDTO> imageDTOs;

}

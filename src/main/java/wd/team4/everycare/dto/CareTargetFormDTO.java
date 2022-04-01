package wd.team4.everycare.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import wd.team4.everycare.domain.CareType;
import wd.team4.everycare.domain.Gender;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CareTargetFormDTO {

    private String name;
    private Gender gender;
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
    private CareType careType;
    private int coronaTest;
    private MultipartFile attachFile;
    private List<MultipartFile> attachFiles;

    @Builder(builderMethodName = "careTargetDTOBuilder")
    public CareTargetFormDTO(String name, Gender gender, LocalDate birth, Long height, Long weight, String zipcode, String address, String detailedAddress, int longTermCareGrade, String comment, int pet, int isCctvAgreement, CareType careType, int coronaTest) {
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.height = height;
        this.weight = weight;
        this.zipcode = zipcode;
        this.address = address;
        this.detailedAddress = detailedAddress;
        this.longTermCareGrade = longTermCareGrade;
        this.comment = comment;
        this.pet = pet;
        this.isCctvAgreement = isCctvAgreement;
        this.careType = careType;
        this.coronaTest = coronaTest;
    }

    @Builder(builderMethodName = "careTargetImageDTOBuilder")
    public CareTargetFormDTO(MultipartFile attachFile, List<MultipartFile> attachFiles) {
        this.attachFile = attachFile;
        this.attachFiles = attachFiles;
    }
}

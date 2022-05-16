package wd.team4.everycare.dto.caretarget;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import wd.team4.everycare.domain.Gender;
import wd.team4.everycare.domain.Member;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CareTargetViewDTO {

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
    private Member member;
    private List<MultipartFile> attachFiles;

    public CareTargetViewDTO(Long id, String name, Gender gender, LocalDate birth, Long height, Long weight, String zipcode, String address, String detailedAddress, int longTermCareGrade, String comment, int pet, int isCctvAgreement, String careType, int coronaTest, Member member) {
        this.id = id;
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
        this.member = member;
    }
}

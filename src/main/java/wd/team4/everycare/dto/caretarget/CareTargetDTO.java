package wd.team4.everycare.dto.caretarget;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import wd.team4.everycare.domain.Gender;
import wd.team4.everycare.domain.Member;

import java.time.LocalDate;

@ToString
@Getter
public class CareTargetDTO {

    private String name;
    private Gender gender;
    private LocalDate birth;
    private Long height;
    private Long weight;
    private String zipcode;
    private String address;
    private String detaildAddress;
    private int longTermCareGrade;
    private String comment;
    private int pet;
    private int isCctvAgrement;
    private String careType;
    private int coronaTest;
    private Member member;

    @Builder
    public CareTargetDTO(String name, Gender gender, LocalDate birth, Long height, Long weight, String zipcode, String address, String detaildAddress, int longTermCareGrade, String comment, int pet, int isCctvAgrement, String careType, int coronaTest, Member member) {
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.height = height;
        this.weight = weight;
        this.zipcode = zipcode;
        this.address = address;
        this.detaildAddress = detaildAddress;
        this.longTermCareGrade = longTermCareGrade;
        this.comment = comment;
        this.pet = pet;
        this.isCctvAgrement = isCctvAgrement;
        this.careType = careType;
        this.coronaTest = coronaTest;
        this.member = member;
    }
}

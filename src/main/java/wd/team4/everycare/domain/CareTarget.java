package wd.team4.everycare.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import wd.team4.everycare.dto.caretarget.CareTargetFormDTO;
import wd.team4.everycare.dto.caretarget.CareTargetViewDTO;
import wd.team4.everycare.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DynamicUpdate
@Getter
@NoArgsConstructor
@Entity
@Table(name = "care_target")
@SequenceGenerator(name = "care_target_seq_generator",
        sequenceName = "care_target_seq",
        initialValue = 1, allocationSize = 1)
public class CareTarget {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "care_target_seq_generator")
    @Column(name = "care_target_id", nullable = false)
    private Long id;

    @Column(name = "care_target_name", length = 30, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "care_target_gender", nullable = false)
    private Gender gender;

    @Column(name = "care_target_birth", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate birth;

    @Column(name = "care_target_height", nullable = false)
    private Long height;

    @Column(name = "care_target_weight", nullable = false)
    private Long weight;

    @Column(name = "care_target_zipcode", length = 45, nullable = false)
    private String zipcode;

    @Column(name = "care_target_address", length = 255, nullable = false)
    private String address;

    @Column(name = "care_target_detailed_address", length = 255, nullable = false)
    private String detailedAddress;

    @Column(name = "care_target_long_term_care_grade", nullable = false)
    private int longTermCareGrade;

    @Column(name = "care_target_specifics", length = 500)
    private String comment;

    @Column(name = "care_target_pet", nullable = false)
    private int pet;

    @Column(name = "care_target_is_cctv_agreement", nullable = false)
    private int isCctvAgreement;

    @Column(name = "care_target_care_type", length = 50, nullable = false)
    private String careType;

    @Column(name = "care_target_corona_test", nullable = false)
    private int coronaTest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "careTarget", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CareTargetImage> careTargetImages = new ArrayList<>();

    @Builder
    public CareTarget(String name, Gender gender, LocalDate birth, Long height, Long weight, String zipcode, String address, String detailedAddress, int longTermCareGrade, String comment, int pet, int isCctvAgreement, String careType, int coronaTest, Member member) {
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

    public void updateInfo(CareTargetFormDTO careTargetFormDTO) {
        if(StringUtils.isNotBlank(careTargetFormDTO.getName())) {
            this.name = careTargetFormDTO.getName();
        }
        if(StringUtils.isNotBlank(String.valueOf(careTargetFormDTO.getGender()))) {
            this.gender = careTargetFormDTO.getGender();
        }
        if(StringUtils.isNotBlank(String.valueOf(careTargetFormDTO.getBirth()))) {
            this.birth = careTargetFormDTO.getBirth();
        }
        if(StringUtils.isNotBlank(String.valueOf(careTargetFormDTO.getHeight()))) {
            this.height = careTargetFormDTO.getHeight();
        }
        if(StringUtils.isNotBlank(String.valueOf(careTargetFormDTO.getWeight()))) {
            this.weight = careTargetFormDTO.getWeight();
        }
        if(StringUtils.isNotBlank(careTargetFormDTO.getZipcode())) {
            this.zipcode = careTargetFormDTO.getZipcode();
        }
        if(StringUtils.isNotBlank(careTargetFormDTO.getDetailedAddress())) {
            this.address = careTargetFormDTO.getAddress();
        }
        if(StringUtils.isNotBlank(careTargetFormDTO.getDetailedAddress())) {
            this.detailedAddress = careTargetFormDTO.getDetailedAddress();
        }
        if(StringUtils.isNotBlank(String.valueOf(careTargetFormDTO.getLongTermCareGrade()))) {
            this.longTermCareGrade = careTargetFormDTO.getLongTermCareGrade();
        }
        if(StringUtils.isNotBlank(careTargetFormDTO.getComment())) {
            this.comment = careTargetFormDTO.getComment();
        }
        if(StringUtils.isNotBlank(String.valueOf(careTargetFormDTO.getPet()))) {
            this.pet = careTargetFormDTO.getPet();
        }
        if(StringUtils.isNotBlank(String.valueOf(careTargetFormDTO.getIsCctvAgreement()))) {
            this.isCctvAgreement = careTargetFormDTO.getIsCctvAgreement();
        }
        if(StringUtils.isNotBlank(careTargetFormDTO.getCareType())) {
            this.careType = careTargetFormDTO.getCareType();
        }
        if(StringUtils.isNotBlank(String.valueOf(careTargetFormDTO.getCoronaTest()))) {
            this.coronaTest = careTargetFormDTO.getCoronaTest();
        }
    }

    public CareTargetFormDTO toFormDTO(){
        return CareTargetFormDTO.builder()
                .name(this.name)
                .gender(this.gender)
                .birth(this.birth)
                .height(this.height)
                .weight(this.weight)
                .zipcode(this.zipcode)
                .address(this.address)
                .detailedAddress(this.detailedAddress)
                .longTermCareGrade(this.longTermCareGrade)
                .comment(this.comment)
                .pet(this.pet)
                .isCctvAgreement(this.isCctvAgreement)
                .careType(this.careType)
                .coronaTest(this.coronaTest)
                .build();
    }

    public CareTargetViewDTO toViewDTO(){
        return CareTargetViewDTO.builder()
                .id(this.id)
                .name(this.name)
                .gender(this.gender)
                .birth(this.birth)
                .height(this.height)
                .weight(this.weight)
                .zipcode(this.zipcode)
                .address(this.address)
                .detailedAddress(this.detailedAddress)
                .longTermCareGrade(this.longTermCareGrade)
                .comment(this.comment)
                .pet(this.pet)
                .isCctvAgreement(this.isCctvAgreement)
                .careType(this.careType)
                .coronaTest(this.coronaTest)
                .build();
    }

    public JobOfferCareTargetDTO toJobOfferCareTargetDTO(){
        return JobOfferCareTargetDTO.builder()
                .name(this.name)
                .gender(String.valueOf(this.gender))
                .height(this.height)
                .weight(this.weight)
                .build();
    }
}

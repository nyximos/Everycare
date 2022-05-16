package wd.team4.everycare.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.dto.careSitter.CareSitterDTO;
import wd.team4.everycare.dto.careSitter.CareSitterFormDTO;
import wd.team4.everycare.dto.careSitter.CareSitterNameDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.DetailJobSearchDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.JobSearchDTO;
import wd.team4.everycare.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DynamicUpdate
@Getter
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "care_sitter_seq_generator",
        sequenceName = "care_sitter_seq",
        initialValue = 1, allocationSize = 1)
public class CareSitter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "care_sitter_seq_generator")
    @Column(name = "care_sitter_id")
    private Long id;

    @Column(name = "care_sitter_name", length = 50, nullable = false)
    private String name;

    @Column(name = "care_sitter_preferred_care_type", length = 50, nullable = false)
    private String preferredType;

    @Column(name = "care_sitter_hopeful_region", nullable = false)
    private String hopefulRegion;

    @Column(name = "care_sitter_desired_day_week", length = 50, nullable = false)
    private String desiredDayWeek;

    @Column(name = "care_sitter_desired_activity_time", nullable = false)
    private String activityTime;

    @Column(name = "care_sitter_desired_hourly_wage", length = 50, nullable = false)
    private String desiredHourlyWage;

    @Column(name = "care_sitter_desired_monthly_wage", length = 50, nullable = false)
    private String desiredMonthlyWage;

    @Column(name = "care_sitter_cctv_agreement", nullable = false)
    private int cctvAgreement;

    @Column(name = "care_sitter_is_vaccinated", nullable = false)
    private int vaccination;

    @Column(name = "care_sitter_introduction", length = 1000, nullable = false)
    private String introduction;

    @Column(name = "care_sitter_disclosure_status", nullable = false)
    private int disclosureStatus;

    @Column(name = "care_sitter_created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "care_sitter_updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "careSitter", fetch = FetchType.LAZY)
    private List<CareSitterImage> careSitterImages = new ArrayList<>();

    @Builder
    public CareSitter(String name, String preferredType, String hopefulRegion, String desiredDayWeek, String activityTime, String desiredHourlyWage, String desiredMonthlyWage, int cctvAgreement, int vaccination, String introduction, int disclosureStatus, LocalDateTime createdAt, LocalDateTime updatedAt, Member member) {
        this.name = name;
        this.preferredType = preferredType;
        this.hopefulRegion = hopefulRegion;
        this.desiredDayWeek = desiredDayWeek;
        this.activityTime = activityTime;
        this.desiredHourlyWage = desiredHourlyWage;
        this.desiredMonthlyWage = desiredMonthlyWage;
        this.cctvAgreement = cctvAgreement;
        this.vaccination = vaccination;
        this.introduction = introduction;
        this.disclosureStatus = disclosureStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.member = member;
    }

    public CareSitterNameDTO toNameDTO(){
        return CareSitterNameDTO.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }

    public CareSitterDTO toCareSitterDTO(){
        return CareSitterDTO.builder()
                .id(this.id)
                .name(this.name)
                .vaccination(this.vaccination)
                .introduction(this.introduction)
                .preferredType(this.preferredType)
                .build();
    }

    public JobSearchDTO toJobSearchDTO(){
        return JobSearchDTO.builder()
                .id(this.id)
                .cctvAgreement(this.cctvAgreement)
                .is_vaccinated(this.vaccination)
                .desiredDayWeek(this.desiredDayWeek)
                .activityTime(this.activityTime)
                .hourlyWage(this.desiredHourlyWage)
                .monthlyWage(this.desiredMonthlyWage)
                .hopefulRegion(this.hopefulRegion)
                .preferredType(this.preferredType)
                .introduction(this.introduction)
                .memberDTO(this.member.toJobOfferMemberDTO())
                .attachFiles(this.careSitterImages)
                .build();
    }

    public DetailJobSearchDTO toDetailJobSearchDTO(){
        return DetailJobSearchDTO.builder()
                .cctvAgreement(this.cctvAgreement)
                .is_vaccinated(this.vaccination)
                .desiredDayWeek(this.desiredDayWeek)
                .activityTime(this.activityTime)
                .hourlyWage(this.desiredHourlyWage)
                .monthlyWage(this.desiredMonthlyWage)
                .hopefulRegion(this.hopefulRegion)
                .preferredType(this.preferredType)
                .introduction(this.introduction)
                .memberDTO(this.member.toJobOfferMemberDTO())
                .build();
    }

    public void updateInfo(CareSitterFormDTO careSitterFormDTO) {
        if (StringUtils.isNotBlank(careSitterFormDTO.getPreferredType()))
            this.preferredType = careSitterFormDTO.getPreferredType();
        if (StringUtils.isNotBlank(careSitterFormDTO.getHopefulRegion()))
            this.hopefulRegion = careSitterFormDTO.getHopefulRegion();
        if (StringUtils.isNotBlank(careSitterFormDTO.getDesiredDayWeek()))
            this.desiredDayWeek = careSitterFormDTO.getDesiredDayWeek();
        if (StringUtils.isNotBlank(careSitterFormDTO.getActivityTime()))
            this.activityTime = careSitterFormDTO.getActivityTime();
        if (StringUtils.isNotBlank(careSitterFormDTO.getDesiredHourlyWage()))
            this.desiredHourlyWage = careSitterFormDTO.getDesiredHourlyWage();
        if (StringUtils.isNotBlank(careSitterFormDTO.getDesiredMonthlyWage()))
            this.desiredMonthlyWage = careSitterFormDTO.getDesiredMonthlyWage();
        if (StringUtils.isNotBlank(String.valueOf(careSitterFormDTO.getCctvAgreement())))
            this.cctvAgreement = careSitterFormDTO.getCctvAgreement();
        if (StringUtils.isNotBlank(String.valueOf(careSitterFormDTO.getVaccination())))
            this.vaccination = careSitterFormDTO.getVaccination();
        if (StringUtils.isNotBlank(careSitterFormDTO.getIntroduction()))
            this.introduction = careSitterFormDTO.getIntroduction();
        if (StringUtils.isNotBlank(String.valueOf(careSitterFormDTO.getDisclosureStatus())))
            this.disclosureStatus = careSitterFormDTO.getDisclosureStatus();
        this.updatedAt = LocalDateTime.now();
    }
}

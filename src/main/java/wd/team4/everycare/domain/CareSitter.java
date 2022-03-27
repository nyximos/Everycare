package wd.team4.everycare.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "care_sitter_seq_generator",
        sequenceName = "care_sitter_seq",
        initialValue = 1, allocationSize = 1)

public class CareSitter {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "care_sitter_seq_generator")
    @Column(name = "care_sitter_id")
    private Long id;

    @Column(name = "care_sitter_file_name")
    private String fileName;

    @Column(name = "care_sitter_file_path")
    private String filePath;

    @Column(name = "care_sitter_preferred_care_type", length = 50, nullable = false)
    private String preferredType;

    @Column(name = "care_sitter_desired_day_week", length = 50, nullable = false)
    private String desiredDayWeek;

    @Column(name = "care_sitter_activity_time", length = 2, nullable = false)
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

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public CareSitter(String preferredType, String desiredDayWeek, String activityTime, String desiredHourlyWage, String desiredMonthlyWage, int cctvAgreement, int vaccination, String introduction, int disclosureStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.preferredType = preferredType;
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
    }
}

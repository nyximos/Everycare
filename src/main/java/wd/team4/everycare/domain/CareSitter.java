package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @Column(name = "care_sitter_profile_id")
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

    @Column(name = "care_sitter_cctv_agreement", length = 5, nullable = false)  //enum
    private String cctvAgreement;

    @Column(name = "care_sitter_is_vaccinated", length = 5, nullable = false)   //enum
    private String vaccination;

    @Column(name = "care_sitter_introduction", length = 1000, nullable = false)
    private String introduction;

    @Column(name = "care_sitter_disclosure_status", length = 5, nullable = false) //enum
    private String disclosureStatus;

    @Column(name = "care_sitter_created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "care_sitter_updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime updatedAt;
    /**
     * TODO 회원ID 외래키 연결
     * */
}

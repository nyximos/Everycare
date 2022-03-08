package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "job_offer")
@SequenceGenerator(name = "job_offer_seq_generator",
        sequenceName = "job_offer_seq",
        initialValue = 1, allocationSize = 1)
public class JobOffer {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_offer_generator")
    @Column(name = "job_offer_id", nullable = false)
    private Long id;

    @Column(name = "job_offer_start_date", nullable = false)
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate startDate;

    @Column(name = "job_offer_end_date", nullable = false)
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate endDate;

    @Column(name = "job_offer_desired_day_week", length = 50)
    private String desiredDayWeek;

    @Column(name = "job_offer_desired_start_time",length = 4)
    private String desiredStartTime;

    @Column(name = "job_offer_activity_time", length = 4)
    private String desiredActivityTime;

    @Column(name = "job_offer_pay", nullable = false)
    private int pay;

    @Column(name = "job_offer_desired_care_sitter_gender", nullable = false)
    private String desiredCareSitterGender; // enum

    @Column(name = "job_offer_comment", length = 500)
    private String comment;
}

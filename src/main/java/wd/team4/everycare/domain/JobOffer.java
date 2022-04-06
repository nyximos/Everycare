package wd.team4.everycare.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.Gender;
import wd.team4.everycare.dto.JobOfferDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "job_offer")
@SequenceGenerator(name = "job_offer_seq_generator",
        sequenceName = "job_offer_seq",
        initialValue = 1, allocationSize = 1)
public class JobOffer {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_offer_seq_generator")
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

    @Enumerated(EnumType.STRING)
    @Column(name = "job_offer_desired_care_sitter_gender", nullable = false)
    private Gender desiredCareSitterGender;

    @Column(name = "job_offer_comment", length = 500)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_target_id")
    private CareTarget careTarget;

    @Builder
    public JobOffer(Long id, LocalDate startDate, LocalDate endDate, String desiredDayWeek, String desiredStartTime, String desiredActivityTime, int pay, Gender desiredCareSitterGender, String comment) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.desiredDayWeek = desiredDayWeek;
        this.desiredStartTime = desiredStartTime;
        this.desiredActivityTime = desiredActivityTime;
        this.pay = pay;
        this.desiredCareSitterGender = desiredCareSitterGender;
        this.comment = comment;
    }

    public JobOffer(Long id, LocalDate startDate, LocalDate endDate, String desiredDayWeek, String desiredStartTime, String desiredActivityTime, int pay, String comment, Gender desiredCareSitterGender) {
    }
}

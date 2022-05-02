package wd.team4.everycare.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.Gender;
import wd.team4.everycare.dto.CareTargetFormDTO;
import wd.team4.everycare.dto.JobOfferDTO;
import wd.team4.everycare.util.StringUtils;

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

    @Column(name = "job_offer_title", nullable = false)
    private String title;

    @Column(name = "job_offer_start_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate startDate;

    @Column(name = "job_offer_end_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate endDate;

    @Column(name = "job_offer_desired_day_week", length = 50)
    private String desiredDayWeek;

    @Column(name = "job_offer_desired_start_time",length = 5)
    private String desiredStartTime;

    @Column(name = "job_offer_end_time", length = 5)
    private String desiredEndTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="care_target_schedule_id")
    private CareTargetSchedule careTargetSchedule;

    @Builder
    public JobOffer(String title, LocalDate startDate, LocalDate endDate, String desiredDayWeek, String desiredStartTime, String desiredEndTime, int pay, Gender desiredCareSitterGender, String comment, CareTarget careTarget, CareTargetSchedule careTargetSchedule, Member member) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.desiredDayWeek = desiredDayWeek;
        this.desiredStartTime = desiredStartTime;
        this.desiredEndTime = desiredEndTime;
        this.pay = pay;
        this.desiredCareSitterGender = desiredCareSitterGender;
        this.comment = comment;
        this.careTarget = careTarget;
        this.careTargetSchedule = careTargetSchedule;
        this.member = member;
    }

    public void updateInfo(JobOfferDTO jobOfferDTO) {
        if(StringUtils.isNotBlank(jobOfferDTO.getTitle())) this.comment = jobOfferDTO.getTitle();
        if(StringUtils.isNotBlank(jobOfferDTO.getStartDate().toString())) this.startDate = jobOfferDTO.getStartDate();
        if(StringUtils.isNotBlank(jobOfferDTO.getEndDate().toString())) this.endDate = jobOfferDTO.getEndDate();
        if(StringUtils.isNotBlank(jobOfferDTO.getDesiredDayWeek())) this.desiredDayWeek = jobOfferDTO.getDesiredDayWeek();
        if(StringUtils.isNotBlank(jobOfferDTO.getDesiredStartTime())) this.desiredStartTime = jobOfferDTO.getDesiredStartTime();
        if(StringUtils.isNotBlank(jobOfferDTO.getDesiredEndTime())) this.desiredEndTime = jobOfferDTO.getDesiredEndTime();
        if(StringUtils.isNotBlank(String.valueOf(jobOfferDTO.getPay()))) this.pay = jobOfferDTO.getPay();
        if(StringUtils.isNotBlank(String.valueOf(jobOfferDTO.getDesiredCareSitterGender()))) this.desiredCareSitterGender = jobOfferDTO.getDesiredCareSitterGender();
        if(StringUtils.isNotBlank(jobOfferDTO.getComment())) this.comment = jobOfferDTO.getComment();
        if(jobOfferDTO.getCareTarget()!=null) this.careTarget = jobOfferDTO.getCareTarget();
        if(jobOfferDTO.getCareTargetSchedule()!=null) this.careTargetSchedule = jobOfferDTO.getCareTargetSchedule();
    }
}

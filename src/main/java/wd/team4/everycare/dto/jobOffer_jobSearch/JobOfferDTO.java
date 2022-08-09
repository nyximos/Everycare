package wd.team4.everycare.dto.jobOffer_jobSearch;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.domain.CareTargetImage;
import wd.team4.everycare.domain.Gender;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.dto.careTargetSchedule.CareTargetScheduleListDTO;
import wd.team4.everycare.dto.caretarget.CareTargetDTO;
import wd.team4.everycare.dto.member.MemberListViewDTO;

import java.time.LocalDate;
import java.util.List;

@ToString
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferDTO {
    private Long id;
    private String title;
//    private CareTarget careTarget;                      //케어 대상인
//    private CareTargetSchedule careTargetSchedule;      //스케줄
//    private Member member;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;                        ////시작일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;                          ////종료일
    private String desiredDayWeek;                      ////희망 요일
    private Gender desiredCareSitterGender;             ////희망 케어시터 성별
    private int pay;                                    ////급여
    private int amount;
    private String comment;                             ////코멘트
    private String desiredStartTime;                    //시작시간
    private String desiredEndTime;                      //종료시간
    private MemberListViewDTO member;
    private JobOfferCareTargetDTO careTarget;                      //케어 대상인
    private CareTargetScheduleListDTO careTargetSchedule;      //스케줄
    private String careTargetImage;

    public JobOffer toJobOffer(){
        return JobOffer.builder()
                .amount(this.amount)
                .title(this.title)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .day(this.desiredDayWeek)
                .desiredStartTime(desiredStartTime)
                .desiredEndTime(this.desiredEndTime)
                .pay(this.pay)
                .desiredCareSitterGender(this.desiredCareSitterGender)
                .comment(this.comment)
                .careTarget(this.careTarget.toCareTarget())
                .careTargetSchedule(this.careTargetSchedule.toCareTargetSchedule())
                .member(this.member.toMember())
                .build();
    }
}

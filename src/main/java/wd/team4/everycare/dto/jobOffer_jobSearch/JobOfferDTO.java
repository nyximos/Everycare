package wd.team4.everycare.dto.jobOffer_jobSearch;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import wd.team4.everycare.domain.Gender;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.dto.careTargetSchedule.CareTargetScheduleListDTO;
import wd.team4.everycare.dto.member.MemberListViewDTO;

import java.time.LocalDate;

@Getter @Setter
@Builder
public class JobOfferDTO {
    private Long id;
    private String title;
    private LocalDate startDate;                        ////시작일
    private LocalDate endDate;                          ////종료일
    private String desiredDayWeek;                      ////희망 요일
    private Gender desiredCareSitterGender;             ////희망 케어시터 성별
    private int pay;                                    ////급여
    private String comment;                             ////코멘트
    private String desiredStartTime;                    //시작시간
    private String desiredEndTime;                      //종료시간
    private MemberListViewDTO member;
    private JobOfferCareTargetDTO careTarget;                      //케어 대상인
    private CareTargetScheduleListDTO careTargetSchedule;      //스케줄

    public JobOffer toJobOffer(){
        return JobOffer.builder()
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

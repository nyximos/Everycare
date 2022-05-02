package wd.team4.everycare.dto;

import lombok.Getter;
import lombok.Setter;
import wd.team4.everycare.domain.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class JobOfferDTO {

    private CareTarget careTarget;                      //케어 대상인
    private CareTargetSchedule careTargetSchedule;      //스케줄
    private String title;
    private Member member;
    private LocalDate startDate;                        ////시작일
    private LocalDate endDate;                          ////종료일
    private String desiredDayWeek;                      ////희망 요일
    private Gender desiredCareSitterGender;             ////희망 케어시터 성별
    private int pay;                                    ////급여
    private String comment;                             ////코멘트
    private String desiredStartTime;                    //시작시간
    private String desiredEndTime;                      //종료시간

    public JobOffer toJobOffer(){
        return JobOffer.builder()
                .startDate(this.startDate)
                .endDate(this.endDate)
                .desiredDayWeek(this.desiredDayWeek)
                .desiredStartTime(desiredStartTime)
                .desiredEndTime(this.desiredEndTime)
                .pay(this.pay)
                .comment(this.comment)
                .desiredCareSitterGender(this.desiredCareSitterGender)
                .careTarget(this.careTarget)
                .careTargetSchedule(this.careTargetSchedule)
                .member(member)
                .build();
    }
}

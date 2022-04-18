package wd.team4.everycare.dto;

import lombok.Getter;
import lombok.Setter;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetSchedule;
import wd.team4.everycare.domain.Gender;
import wd.team4.everycare.domain.JobOffer;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class JobOfferDTO {

    private Long id;
    private String comment;                     //코멘트
    private String desiredActivityTime;         //희망활동시간
    private Gender desiredCareSitterGender;     //희망 케어시터 성별
    private String desiredDayWeek;              //희망 요일
    private String desiredStartTime;            //시작시간
    private LocalDate endDate;                  //종료일
    private int pay;                            //급여
    private LocalDate startDate;                //시작일
    private CareTarget careTarget;
    private CareTargetSchedule careTargetSchedule;

    public JobOfferDTO(JobOffer jobOffer){
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

    public JobOfferDTO() {
    }

    public JobOffer toListEntity(){
        return new JobOffer(id, startDate, endDate, desiredDayWeek, desiredStartTime, desiredActivityTime, pay, comment, desiredCareSitterGender);
    }
}

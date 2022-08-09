package wd.team4.everycare.dto.jobOffer_jobSearch;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import wd.team4.everycare.domain.ActivityClassification;
import wd.team4.everycare.domain.Gender;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.dto.careNote.ActivityInformationDTO;
import wd.team4.everycare.dto.careTargetSchedule.ActivityClassificationDTO;
import wd.team4.everycare.dto.careTargetSchedule.CareTargetScheduleListDTO;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Builder
public class DetailJobOfferDTO {

    private String memberId;
    private String title;           //
    private String startDate;              //
    private String endDate;             //
    private String desiredDayWeek;          //
    private String desiredStartTime;                 //
    private String desiredEndTime;                   //
    private int pay;                                //
    private String comment;                          //
    private Gender desiredCareSitterGender;   //
    private JobOfferCareTargetDTO careTarget;      //
    private CareTargetScheduleListDTO careTargetScheduleListDTO;
    private List<ActivityInformationDTO> activityInformationDTO;
    private List<ActivityClassificationDTO> activityClassificationsDTO;
    private String careTargetImages;

    public JobOffer toJobOffer(){
        return JobOffer.builder()
                .title(this.title)
                .startDate(LocalDate.parse(this.startDate))
                .endDate(LocalDate.parse(this.endDate))
                .day(this.desiredDayWeek)
                .desiredStartTime(this.desiredStartTime)
                .desiredEndTime(this.desiredEndTime)
                .pay(this.pay)
                .comment(this.comment)
                .desiredCareSitterGender(this.desiredCareSitterGender)
                .careTarget(this.careTarget.toCareTarget())
                .careTargetSchedule(this.careTargetScheduleListDTO.toCareTargetSchedule())
                .build();
    }
}

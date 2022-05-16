package wd.team4.everycare.dto.jobOffer_jobSearch;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import wd.team4.everycare.domain.*;
import wd.team4.everycare.dto.careTargetSchedule.CareTargetScheduleListDTO;

@Getter @Setter
@Builder
public class DetailJobOfferDTO {

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


}

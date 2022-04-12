package wd.team4.everycare.dto;

import lombok.Getter;
import lombok.Setter;
import wd.team4.everycare.domain.*;

@Getter @Setter
public class CareTargetScheduleDTO {

    private Long id;
    private String name;
    private String startTime;
    private String endTime;
    private CareTarget careTarget;
    private Contract contract;
    private CareSitter careSitter;
    private CareNote careNote;
    private JobOffer jobOffer;
}

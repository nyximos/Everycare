package wd.team4.everycare.dto;

import lombok.Getter;
import lombok.Setter;
import wd.team4.everycare.domain.Gender;

@Getter @Setter
public class CareTargetAndScheduleDTO {

    private long careTargetId;
    private long careTargetScheduleId;
    private Gender gender;
    private String comment;
}

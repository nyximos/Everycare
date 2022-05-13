package wd.team4.everycare.dto.careTargetSchedule;

import lombok.*;
import wd.team4.everycare.domain.CareTargetSchedule;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CareTargetScheduleListDTO {

    private Long id;
    private String name;
    private String startTime;
    private String endTime;

    public CareTargetSchedule toCareTargetSchedule(){
        return CareTargetSchedule.builder()
                .id(id)
                .name(name)
                .endTime(endTime)
                .build();
    }

}

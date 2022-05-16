package wd.team4.everycare.dto.careTargetSchedule;

import lombok.*;
import wd.team4.everycare.domain.CareTargetSchedule;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CareTargetScheduleDTO {

    private Long id;
    private String name;
    private String startTime;
    private String endTime;

}

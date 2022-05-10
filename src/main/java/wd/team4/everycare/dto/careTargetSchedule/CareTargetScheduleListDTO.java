package wd.team4.everycare.dto.careTargetSchedule;

import lombok.*;

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

}

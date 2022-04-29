package wd.team4.everycare.dto;

import lombok.*;

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
    private Long careTarget;
}

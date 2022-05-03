package wd.team4.everycare.dto.careTargetSchedule;

import lombok.*;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActivityInformationFormDTO {

    private String startTime;
    private String endTime;
    private String requirement;
    private Long activityClassificationId;
    private Long scheduleId;

}

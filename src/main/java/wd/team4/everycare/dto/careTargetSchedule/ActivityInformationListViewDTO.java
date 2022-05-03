package wd.team4.everycare.dto.careTargetSchedule;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActivityInformationListViewDTO {
    private Long id;
    private String startTime;
    private String endTime;
    private String requirement;
    private ActivityClassificationDTO activityClassificationDTO;
}

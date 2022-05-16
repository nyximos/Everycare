package wd.team4.everycare.dto.careTargetSchedule;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActivityInformationListViewDTO {

    private CareTargetScheduleDTO careTargetScheduleDTO;
    private List<ActivityInformationViewDTO> activityInformationViewDTOs;

}

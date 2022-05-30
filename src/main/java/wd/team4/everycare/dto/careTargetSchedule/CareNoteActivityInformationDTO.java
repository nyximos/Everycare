package wd.team4.everycare.dto.careTargetSchedule;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareNoteActivityInformationDTO {

    private Long id;
    private String name;
    private String startTime;
    private String endTime;
    private String requirement;

}

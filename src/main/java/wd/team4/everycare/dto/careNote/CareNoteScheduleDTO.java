package wd.team4.everycare.dto.careNote;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CareNoteScheduleDTO {

    private Long id;
    private String startTime;
    private String endTime;
    private String requirement;
    private String content;
    private String storeFileName;
    private String activityClassificationName;

}

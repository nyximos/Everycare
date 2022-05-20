package wd.team4.everycare.dto.careNote;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareNoteListDTO {

    private Long id;
    private String startTime;
    private String endTime;
    private String careTargetName;

}

package wd.team4.everycare.dto.careRecord;

import lombok.*;
import wd.team4.everycare.domain.CareNote;
import wd.team4.everycare.domain.HealthStatus;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HealthRecordFormDTO {

    private Long id;
    private HealthStatus healthStatus;
    private String detailComment;
    private Long healthClassificationId;

}

package wd.team4.everycare.dto.careTargetSchedule;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActivityClassificationDTO {
    private Long id;
    private String name;
    private int level;
    private Long ParentId;
}

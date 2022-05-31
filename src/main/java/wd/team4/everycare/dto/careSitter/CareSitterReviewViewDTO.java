package wd.team4.everycare.dto.careSitter;

import lombok.*;
import wd.team4.everycare.dto.careTargetSchedule.ActivityClassificationDTO;
import wd.team4.everycare.dto.member.MemberNameDTO;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CareSitterReviewViewDTO {

    private Long rating;
    private String comment;
    private ActivityClassificationDTO activityClassification;
    private MemberNameDTO member;
}

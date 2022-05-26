package wd.team4.everycare.dto.careSitterReview;

import lombok.*;
import wd.team4.everycare.domain.ActivityClassification;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareSitterReviewUpdateFormDTO {

    private Long rating;
    private String comment;
    private Long activityClassificationId;
    private ActivityClassification activityClassification;

}

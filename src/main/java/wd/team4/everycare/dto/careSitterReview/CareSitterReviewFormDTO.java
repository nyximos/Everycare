package wd.team4.everycare.dto.careSitterReview;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareSitterReviewFormDTO {

    private Long rating;
    private String comment;
    private Long activityClassificationId;

}

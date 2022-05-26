package wd.team4.everycare.dto.careSitterReview;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareSitterReviewDTO {

    private Long id;
    private Long rating;
    private String comment;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime updatedAt;
    private Long activityClassificationId;
    private String activityClassificationName;
    private Long careTargetScheduleId;

}

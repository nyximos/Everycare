package wd.team4.everycare.dto.careSitterReview;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareSitterReviewListDTO {

    private Long id;
    private Long rating;
    private Long comment;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;
    private Long careTargetScheduleId;

}

package wd.team4.everycare.dto.careNote;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareSitterCareNoteReviewDTO {

    private Long id;
    private Long rating;
    private String comment;
    private LocalDateTime updatedAt;
    private String categoryName;

}

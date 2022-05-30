package wd.team4.everycare.dto.careNote;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareSitterCompletionCareNoteDTO {

    private Long id;
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String storeFileName;

}

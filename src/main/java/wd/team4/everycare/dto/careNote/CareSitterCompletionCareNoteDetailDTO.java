package wd.team4.everycare.dto.careNote;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.dto.careTargetSchedule.CareNoteActivityInformationDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareSitterCompletionCareNoteDetailDTO {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime endTime;
    private String storeFileName;
    private String careTargetName;
    private List<CareNoteActivityInformationDTO> careNoteActivityInformationDTOs;
    private List<CareSitterCareNoteReviewDTO> careSitterCareNoteReviewDTOs;
}

package wd.team4.everycare.dto.careNote;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CareNoteScheduleDTO {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String startTime; // 스케줄 시작시간
    private String endTime;   // 스케줄 종료시간
    private List<ActivityInformationDTO> activityInformationDTOs;

}

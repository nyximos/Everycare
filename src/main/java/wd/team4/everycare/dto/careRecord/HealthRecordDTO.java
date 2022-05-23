package wd.team4.everycare.dto.careRecord;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.domain.HealthStatus;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HealthRecordDTO {

    private Long id;
    private HealthStatus healthStatus;
    private String detailComment;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime updatedAt;
    private String healthClassification;

}
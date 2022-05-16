package wd.team4.everycare.dto.careSitter;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.dto.ImageDTO;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CareSitterDTO {
    private Long id;
    private String name;
    private String preferredType;
    private String hopefulRegion;
    private String desiredDayWeek;
    private String activityTime;
    private String desiredHourlyWage;
    private String desiredMonthlyWage;
    private int cctvAgreement;
    private int vaccination;
    private String introduction;
    private int disclosureStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime updatedAt;
    private List<ImageDTO> imageDTOs;

}

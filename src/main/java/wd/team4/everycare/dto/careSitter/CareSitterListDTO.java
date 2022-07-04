package wd.team4.everycare.dto.careSitter;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.domain.Gender;
import wd.team4.everycare.dto.CertificationNameDTO;
import wd.team4.everycare.dto.badge.BadgeNameDTO;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareSitterListDTO {

    private Long id;
    private int cctvAgreement;
    private int isVaccinated;
    private String desiredDayWeek;
    private String activityTime;
    private String hourlyWage;
    private String monthlyWage;
    private String hopefulRegion;
    private String preferredType;

    private String name;
    private int age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
    private Gender gender;

    private List<String> storeFileNames;

    private List<BadgeNameDTO> badge;
    private List<CertificationNameDTO> certification;
}

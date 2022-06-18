package wd.team4.everycare.dto.jobOffer_jobSearch;

import lombok.*;
import wd.team4.everycare.domain.CareSitterImage;
import wd.team4.everycare.domain.Certification;
import wd.team4.everycare.dto.badge.CareSitterBadgeDTO;
import wd.team4.everycare.dto.member.JobOfferMemberDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobSearchDTO {
    private Long id;
    private int disclosureStatus;
    private int cctvAgreement;
    private int is_vaccinated;
    private String desiredDayWeek;
    private String activityTime;
    private String hourlyWage;
    private String monthlyWage;
    private String hopefulRegion;
    private String preferredType;
    private String introduction;
    private JobOfferMemberDTO memberDTO;
    private List<CareSitterImage> attachFiles;
    private List<Certification> certifications;
    private List<CareSitterBadgeDTO> badge;

}

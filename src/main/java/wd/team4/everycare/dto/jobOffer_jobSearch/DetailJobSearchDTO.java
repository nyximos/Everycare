package wd.team4.everycare.dto.jobOffer_jobSearch;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import wd.team4.everycare.domain.CareSitterImage;
import wd.team4.everycare.domain.Certification;
import wd.team4.everycare.dto.CertificationViewDTO;
import wd.team4.everycare.dto.careSitter.CareSitterImageDTO;
import wd.team4.everycare.dto.member.JobOfferMemberDTO;

import java.util.List;

@Builder
@Setter @Getter
public class DetailJobSearchDTO {

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
    private List<CareSitterImageDTO> attachFiles;
    private List<CertificationViewDTO> certification;

}
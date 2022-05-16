package wd.team4.everycare.dto.contract;

import lombok.*;
import wd.team4.everycare.domain.CareSitterImage;
import wd.team4.everycare.dto.careSitter.CareSitterDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.DetailJobOfferDTO;
import wd.team4.everycare.dto.member.JobOfferMemberDTO;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO {

    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String startTime;
    private String endTime;
    private int pay;
    private int contractStatus;
    private DetailJobOfferDTO jobOfferDTO;
    private JobOfferMemberDTO memberDTO;
    private CareSitterDTO careSitterDTO;
    private List<CareSitterImage> careSitterImage;
}

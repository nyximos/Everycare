package wd.team4.everycare.dto.contract;

import lombok.*;
import wd.team4.everycare.domain.JobOfferCareSitter;
import wd.team4.everycare.dto.careSitter.CareSitterNameDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.DetailJobOfferDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.JobOfferCareTargetDTO;
import wd.team4.everycare.dto.member.JobOfferMemberDTO;

import java.time.LocalDate;

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
    private CareSitterNameDTO careSitterDTO;
}

package wd.team4.everycare.dto.contract;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.domain.CareSitterImage;
import wd.team4.everycare.domain.Contract;
import wd.team4.everycare.dto.jobOffer_jobSearch.DetailJobOfferDTO;
import wd.team4.everycare.dto.member.JobOfferMemberDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignContractDTO {

    private Long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String startTime;
    private String endTime;
    private int pay;
    private int status;
    private int amount;                     //
    private LocalDateTime payDatetime;
    private String cardCompany;
    private String cardNumber;
    private String payApprove;
    private int monthlyInstallmentPlan;
    private DetailJobOfferDTO jobOfferDTO;
    private JobOfferMemberDTO memberDTO;
    private SignCareSitterContractDTO careSitterDTO;
    private List<CareSitterImage> careSitterImage;

    public Contract toEntity(){
        return Contract.builder()
                .id(this.id)
                .name(this.name)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .pay(this.pay)
                .status(this.status)
                .amount(this.amount)
                .payDateTime(this.payDatetime)
                .cardNumber(this.cardNumber)
                .payApprove(this.payApprove)
                .monthlyInstallmentPlan(this.monthlyInstallmentPlan)
                .jobOffer(this.jobOfferDTO.toJobOffer())
                .member(this.memberDTO.toMember())
                .careSitter(careSitterDTO.toCareSitter())
                .build();
    }
}

package wd.team4.everycare.dto.contract;

import lombok.*;
import wd.team4.everycare.dto.careSitter.CareSitterDTO;
import wd.team4.everycare.dto.member.MemberListViewDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompletionContractDTO {

    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String startTime;
    private String endTime;
    private int pay;
    private int status;
    private int amount;
    private LocalDateTime payDateTime;
    private String cardCompany;
    private String cardNumber;
    private String payApprove;
    private int monthlyInstallmentPlan;
    private CareSitterDTO careSitter;
    private MemberListViewDTO member;
}

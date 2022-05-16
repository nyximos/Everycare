package wd.team4.everycare.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.dto.careSitter.CareSitterNameDTO;
import wd.team4.everycare.dto.contract.ContractDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "contract")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "contract_seq_generator",
        sequenceName = "contract_seq",
        initialValue = 1, allocationSize = 1)
public class Contract {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_seq_generator")
    @Column(name = "contract_id")
    private Long id;

    @Column(name = "contract_name", length = 100, nullable = false)
    private String name;

    @Column(name = "contract_start_date")
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate startDate;

    @Column(name = "contract_end_date")
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate endDate;

    @Column(name = "contract_start_time", length = 5, nullable = false)
    private String startTime;

    @Column(name = "contract_end_time", length = 5, nullable = false)
    private String endTime;

    @Column(name = "contract_pay", nullable = false)
    private int pay;

    @Column(name = "contract_status", nullable = false)
    private int status;

    @Column(name = "contract_pay_amount", nullable = false)
    private int amount;

    @Column(name = "contract_pay_datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime payDatetime;

    @Column(name = "contract_card_company")
    private String cardCompany;

    @Column(name = "contract_card_number", length = 55)
    private String cardNumber;

    @Column(name = "contract_pay_approve")
    private String payApprove;

    @Column(name = "contract_monthly_installment_plan")
    private int monthlyInstallmentPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_sitter_id")
    private CareSitter careSitter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_offer_id", nullable = false)
    private JobOffer jobOffer;

    @Builder
    public Contract (Long id, String name, LocalDate startDate, LocalDate endDate, String startTime, String endTime, int pay, int contractStatus, int amount, LocalDateTime payDateTime, String cardCompany, String cardNumber, String payApprove, int monthlyInstallmentPlan, JobOffer jobOffer, Member member, CareSitter careSitter){
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pay = pay;
        this.contractStatus = contractStatus;
        this.amount = amount;
        this.payDatetime = payDateTime;
        this.cardCompany = cardCompany;
        this.cardNumber = cardNumber;
        this.payApprove = payApprove;
        this.monthlyInstallmentPlan = monthlyInstallmentPlan;
        this.jobOffer = jobOffer;
        this.member = member;
        this.careSitter = careSitter;
    }

    public ContractDTO toContractDTO(){
        return ContractDTO.builder()
                .id(this.id)
                .name(this.name)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .pay(this.pay)
                .contractStatus(this.contractStatus)
                .jobOfferDTO(this.jobOffer.toDetailJobOfferDTO(this.jobOffer))
                .memberDTO(this.member.toJobOfferMemberDTO())
                .careSitterDTO(this.careSitter.toCareSitterDTO())
                .build();
    }
  
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_target_id")
    private CareTarget careTarget;

}

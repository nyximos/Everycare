package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "contract")
@SequenceGenerator(name = "contract_seq_generator",
        sequenceName = "contract_seq",
        initialValue = 1, allocationSize = 1)
public class Contract {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_seq")
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

    @Column(name = "contract_start_time", length = 4, nullable = false)
    private String startTime;

    @Column(name = "contract_end_time", length = 4, nullable = false)
    private String endTime;

    @Column(name = "contract_pay", nullable = false)
    private int pay;

    @Column(name = "contract_status", nullable = false)
    private int contractStatus;

    @Column(name = "contract_pay_amount", nullable = false)
    private int amount;

    @Column(name = "contract_pay_datetime", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime payDatetime;

    @Column(name = "contract_card_company")
    private String cardCompany;

    @Column(name = "contract_card_number", length = 55)
    private String cardNumber;

    @Column(name = "contract_card_approve")
    private String payApprove;

    @Column(name = "contract_monthly_installment_plan")
    private int monthlyInstallmentPlan;

    @OneToMany
    @JoinColumn(name = "care_sitter_profile_id")
    private List<CareSitter> careSitters;

    @OneToMany
    @JoinColumn(name = "member_id", nullable = false)
    private List<Member> members;

    @OneToMany
    @JoinColumn(name = "job_offer_id", nullable = false)
    private List<JobOffer> jobOffers;
}

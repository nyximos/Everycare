package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import wd.team4.everycare.domain.*;
import wd.team4.everycare.repository.CareSitterRepository;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.repository.JobOfferRepository;
import wd.team4.everycare.repository.MemberRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Component
@Transactional
@RequiredArgsConstructor
public class InitContractService {

    private final EntityManager em;
    private final JobOfferRepository jobOfferRepository;
    private final MemberRepository memberRepository;
    private final CareSitterRepository careSitterRepository;

    public void dbInit() {
        JobOffer jobOffer1 = jobOfferRepository.findById(1L).get();
        JobOffer jobOffer2 = jobOfferRepository.findById(2L).get();
        Member member1 = memberRepository.findById("member1").get();
        Member member2 = memberRepository.findById("member2").get();
        CareSitter careSitter1 = careSitterRepository.findByMember(member1);
        CareSitter careSitter2 = careSitterRepository.findByMember(member2);


        Contract contract1 = newContract("contract1", LocalDate.now(), LocalDate.now(), "10:00", "11:00", 1000, 0, jobOffer1, member1, careSitter1);
        Contract contract2 = newContract("contract2", LocalDate.now(), LocalDate.now(), "10:00", "11:00", 1000, 1, jobOffer2, member2, careSitter2);
        Contract contract3 = oldContract("contract3", LocalDate.now(), LocalDate.now(), "10:00", "11:00", 1000, 2, 10000, LocalDateTime.now(), "BC", "123456789", "payApprove1", 1, jobOffer1, member1, careSitter1);
        Contract contract4 = oldContract("contract4", LocalDate.now(), LocalDate.now(), "10:00", "11:00", 1000, 3, 10000, LocalDateTime.now(), "AB", "987654321", "payApprove2", 2, jobOffer2, member2, careSitter2);
    }
    private Contract oldContract(String name, LocalDate startDate, LocalDate endDate, String startTime, String endTime, int pay, int status, int amount, LocalDateTime payDateTime, String cardCompany, String cardNumber, String payApprove, int monthlyInstallmentPlan, JobOffer jobOffer, Member member, CareSitter careSitter){
        Contract contract = Contract.builder()
                .name(name)
                .startDate(startDate)
                .endDate(endDate)
                .startTime(startTime)
                .endTime(endTime)
                .pay(pay)
                .status(status)
                .amount(amount)
                .payDateTime(payDateTime)
                .cardCompany(cardCompany)
                .cardNumber(cardNumber)
                .payApprove(payApprove)
                .monthlyInstallmentPlan(monthlyInstallmentPlan)
                .jobOffer(jobOffer)
                .member(member)
                .careSitter(careSitter)
                .build();

        em.persist(contract);
        return contract;
    }

    private Contract newContract(String name, LocalDate startDate, LocalDate endDate, String startTime, String endTime, int pay, int status, JobOffer jobOffer, Member member, CareSitter careSitter){
        Contract contract = Contract.builder()
                .name(name)
                .startDate(startDate)
                .endDate(endDate)
                .startTime(startTime)
                .endTime(endTime)
                .pay(pay)
                .status(status)
                .jobOffer(jobOffer)
                .member(member)
                .careSitter(careSitter)
                .build();

        em.persist(contract);
        return contract;
    }
}

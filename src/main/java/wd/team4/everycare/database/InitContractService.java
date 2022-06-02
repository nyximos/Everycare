package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import wd.team4.everycare.domain.*;
import wd.team4.everycare.repository.*;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Component
@Transactional
@RequiredArgsConstructor
public class InitContractService {

    private final EntityManager em;
    private final JobOfferRepository jobOfferRepository;
    private final MemberRepository memberRepository;
    private final CareSitterRepository careSitterRepository;
    private final ActivityInformationRepository activityInformationRepository;

    public void dbInit() {

        JobOffer jobOffer1 = jobOfferRepository.findById(1L).get();
        JobOffer jobOffer2 = jobOfferRepository.findById(2L).get();
        Member member1 = memberRepository.findById("member1").get();
        Member member2 = memberRepository.findById("member2").get();
        CareSitter careSitter1 = careSitterRepository.findByMember(member1);
        CareSitter careSitter2 = careSitterRepository.findByMember(member2);

        Contract contract1 = oldContract("표철진_고승희", LocalDate.now(), LocalDate.now(), "09:00", "18:00", 10000, 2, 1500000, LocalDateTime.now(), "BC", "2839746923869", "approvenumber", 0, jobOffer1, jobOffer1.getMember(), careSitter2);

        //케어 노트 생성
        String day = jobOffer1.getDay();
        System.out.println("day = " + day);
        String[] dayArr = day.split(",");
        System.out.println("dayArr[0] = " + dayArr[0]);
        System.out.println("dayArr[1] = " + dayArr[1]);
        System.out.println(dayArr.length);
        for (int i=0; i<dayArr.length; i++) {
            System.out.println("i = " + i);
            System.out.println("dayArr = " + dayArr[i]);
            LocalDate date = LocalDate.parse(dayArr[i], DateTimeFormatter.ISO_DATE);

            //케어 노트 생성
            CareNote careNote = CareNote.builder()
                    .date(date)
                    .contract(contract1)
                    .careSitter(contract1.getCareSitter())
                    .member(contract1.getMember())
                    .build();

            em.persist(careNote);


            // 케어 대상인 스케줄 생성
            CareTargetSchedule careTargetSchedule = CareTargetSchedule.builder()
                    .name(jobOffer1.getCareTargetSchedule().getName())
                    .startTime(jobOffer1.getDesiredStartTime())
                    .endTime(jobOffer1.getDesiredEndTime())
                    .careTarget(jobOffer1.getCareTarget())
                    .contract(contract1)
                    .careSitter(contract1.getCareSitter())
                    .careNote(careNote)
                    .build();

            em.persist(careTargetSchedule);


            // 활동 정보
            List<ActivityInformation> activityInformations = activityInformationRepository.findAllByCareTargetSchedule(jobOffer1.getCareTargetSchedule());
            for(int j=0; j<activityInformations.size(); j++) {
                ActivityInformation activityInformation = activityInformations.get(j);

                ActivityInformation newActivityInformation = ActivityInformation.builder()
                        .startTime(activityInformation.getStartTime())
                        .endTime(activityInformation.getEndTime())
                        .requirement(activityInformation.getRequirement())
                        .activityClassification(activityInformation.getActivityClassification())
                        .careTargetSchedule(careTargetSchedule)
                        .build();

                em.persist(newActivityInformation);
            }

        }


        Contract contract2 = newContract("contract2", LocalDate.now(), LocalDate.now(), "10:00", "11:00", 1000, 0, jobOffer1, member1, careSitter1);
        Contract contract3 = newContract("contract3", LocalDate.now(), LocalDate.now(), "10:00", "11:00", 1000, 1, jobOffer2, member2, careSitter2);
        Contract contract4 = oldContract("contract4", LocalDate.now(), LocalDate.now(), "10:00", "11:00", 1000, 2, 10000, LocalDateTime.now(), "BC", "123456789", "payApprove1", 1, jobOffer1, member1, careSitter1);
        Contract contract5 = oldContract("contract5", LocalDate.now(), LocalDate.now(), "10:00", "11:00", 1000, 3, 10000, LocalDateTime.now(), "AB", "987654321", "payApprove2", 2, jobOffer2, member2, careSitter2);
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

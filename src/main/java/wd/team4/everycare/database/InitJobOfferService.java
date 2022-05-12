package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.*;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.repository.CareTargetScheduleRepository;
import wd.team4.everycare.repository.MemberRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class InitJobOfferService {

    private final EntityManager em;
    private final CareTargetRepository careTargetRepository;
    private final MemberRepository memberRepository;
    private final CareTargetScheduleRepository careTargetScheduleRepository;
/*TODO 왜자꾸 타겟스케줄은 null들가는지 함 물어보자*/
    public void dbInit(){
        Member member = memberRepository.findById("member1").get();
        CareTarget careTarget = careTargetRepository.findAllByMember(member).get(0);
        CareTargetSchedule careTargetSchedule = careTargetScheduleRepository.findAllByCareTargetId(careTarget.getId()).get(0);

        CareTarget careTarget1 = careTarget("name1", Gender.M, LocalDate.now(), 1L, 1L, "zipcode1", "address1", "detailAddress1", 1, "comment1", 1,1, "careType1", 1, member);
        CareTarget careTarget2 = careTarget("name2", Gender.M, LocalDate.now(), 2L, 2L, "zipcode2", "address2", "detailAddress2", 2, "comment2", 2,2, "careType2", 2, member);
        CareTarget careTarget3 = careTarget("name3", Gender.M, LocalDate.now(), 3L, 3L, "zipcode3", "address3", "detailAddress3", 3, "comment3", 3,3, "careType3", 3, member);
        CareTarget careTarget4 = careTarget("name4", Gender.M, LocalDate.now(), 4L, 4L, "zipcode4", "address4", "detailAddress4", 4, "comment4", 4,4, "careType4", 4, member);
        CareTarget careTarget5 = careTarget("name5", Gender.M, LocalDate.now(), 5L, 5L, "zipcode5", "address5", "detailAddress5", 5, "comment5", 5,5, "careType5", 5, member);

        CareTargetSchedule careTargetSchedule1 = careTargetSchedule("name1", "10:00", "10:30", careTarget1);
        CareTargetSchedule careTargetSchedule2 = careTargetSchedule("name1", "11:00", "11:30", careTarget2);
        CareTargetSchedule careTargetSchedule3 = careTargetSchedule("name1", "12:00", "12:30", careTarget3);
        CareTargetSchedule careTargetSchedule4 = careTargetSchedule("name1", "13:00", "13:30", careTarget4);
        CareTargetSchedule careTargetSchedule5 = careTargetSchedule("name1", "14:00", "14:30", careTarget5);

        JobOffer jobOffer1 = jobOffer("title1", LocalDate.now(), LocalDate.now(), "desiredDayWeek1", "10:00", "10:30", 1, Gender.M, "comment1", member, careTarget1, careTargetSchedule1);
        JobOffer jobOffer2 = jobOffer("title2", LocalDate.now(), LocalDate.now(), "desiredDayWeek2", "11:00", "11:30", 2, Gender.M, "comment2", member, careTarget2, careTargetSchedule2);
        JobOffer jobOffer3 = jobOffer("title3", LocalDate.now(), LocalDate.now(), "desiredDayWeek3", "12:00", "12:30", 3, Gender.M, "comment3", member, careTarget3, careTargetSchedule3);
        JobOffer jobOffer4 = jobOffer("title4", LocalDate.now(), LocalDate.now(), "desiredDayWeek4", "13:00", "13:30", 4, Gender.M, "comment4", member, careTarget4, careTargetSchedule4);
        JobOffer jobOffer5 = jobOffer("title5", LocalDate.now(), LocalDate.now(), "desiredDayWeek5", "14:00", "14:30", 5, Gender.M, "comment5", member, careTarget5, careTargetSchedule5);
        System.out.println("jobOffer1 = " + jobOffer1);
    }

    private CareTargetSchedule careTargetSchedule(String name, String startTime, String endTime, CareTarget careTarget){
        CareTargetSchedule careTargetSchedule = CareTargetSchedule.builder()
                .name(name)
                .startTime(startTime)
                .endTime(endTime)
                .careTarget(careTarget)
                .build();
        em.persist(careTarget);

        return careTargetSchedule;
    }

    private CareTarget careTarget(String name, Gender gender, LocalDate birth, Long height, Long weight, String zipcode, String address, String detailedAddress, int longTermCareGrade, String comment, int pet, int isCctvAgreement, String careType, int coronaTest, Member member){
        CareTarget careTarget = CareTarget.builder()
                .name(name)
                .gender(gender)
                .birth(birth)
                .height(height)
                .weight(weight)
                .zipcode(zipcode)
                .address(address)
                .detailedAddress(detailedAddress)
                .longTermCareGrade(longTermCareGrade)
                .comment(comment)
                .pet(pet)
                .isCctvAgreement(isCctvAgreement)
                .careType(careType)
                .coronaTest(coronaTest)
                .member(member)
                .build();
        em.persist(careTarget);

        return careTarget;
    }

    private JobOffer jobOffer(String title, LocalDate startDate, LocalDate endDate, String desiredDayWeek, String desiredStartTime, String desiredEndTime, int pay, Gender desiredCareSitterGender, String comment, Member member, CareTarget careTarget, CareTargetSchedule careTargetSchedule){
        JobOffer jobOffer = JobOffer.builder()
                .title(title)
                .startDate(startDate)
                .endDate(endDate)
                .desiredDayWeek(desiredDayWeek)
                .desiredStartTime(desiredStartTime)
                .desiredEndTime(desiredEndTime)
                .pay(pay)
                .desiredCareSitterGender(desiredCareSitterGender)
                .comment(comment)
                .member(member)
                .careTarget(careTarget)
                .careTargetSchedule(careTargetSchedule)
                .build();
        em.persist(jobOffer);

        return jobOffer;
    }
}

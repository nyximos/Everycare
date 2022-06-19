package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.*;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.repository.CareTargetScheduleRepository;
import wd.team4.everycare.repository.MemberRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;

@Component
@Transactional
@RequiredArgsConstructor
public class InitJobOfferService {

        private final EntityManager em;
        private final CareTargetRepository careTargetRepository;
        private final MemberRepository memberRepository;
        private final CareTargetScheduleRepository careTargetScheduleRepository;

        public void dbInit() {
                Member member = memberRepository.findById("member1").get();

                CareTarget careTarget = careTargetRepository.findById(1L).orElse(null);
                CareTarget careTarget2 = careTargetRepository.findById(2L).orElse(null);
                CareTargetSchedule careTargetSchedule = careTargetScheduleRepository.findById(1L).orElse(null);
                careTargetRepository.findById(1L);

                JobOffer jobOffer1 = jobOffer("케어시터분 구합니다.", LocalDate.parse("2022-05-01"),
                                LocalDate.parse("2022-06-18"), "2022-06-18,2022-06-19", "09:00", "18:00", 10000, 130000,
                                Gender.M, "잘 부탁 드립니다.", member, careTarget, careTargetSchedule);

                JobOffer jobOffer2 = jobOffer("title1", LocalDate.now(), LocalDate.now(), "desiredDayWeek1", "10:00",
                                "10:30", 1, 10, Gender.M, "comment1", member, careTarget, careTargetSchedule);
                JobOffer jobOffer3 = jobOffer("title2", LocalDate.now(), LocalDate.now(), "desiredDayWeek2", "11:00",
                                "11:30", 2, 20, Gender.M, "comment2", member, careTarget, careTargetSchedule);
                JobOffer jobOffer4 = jobOffer("title3", LocalDate.now(), LocalDate.now(), "desiredDayWeek3", "12:00",
                                "12:30", 3, 20, Gender.M, "comment3", member, careTarget2, careTargetSchedule);
                JobOffer jobOffer5 = jobOffer("title4", LocalDate.now(), LocalDate.now(), "desiredDayWeek4", "13:00",
                                "13:30", 4, 40, Gender.M, "comment4", member, careTarget2, careTargetSchedule);
                JobOffer jobOffer6 = jobOffer("title5", LocalDate.now(), LocalDate.now(), "desiredDayWeek5", "14:00",
                                "14:30", 5, 50, Gender.M, "comment5", member, careTarget, careTargetSchedule);
                System.out.println("jobOffer1 = " + jobOffer1);
        }

                JobOffer jobOffer1 = jobOffer("케어시터분 구합니다.", LocalDate.parse("2022-05-01"),
                                LocalDate.parse("2022-06-18"), "2022-06-18,2022-06-19,2022-06-20", "09:00",
                                "18:00", 10000, 130000, Gender.M, "잘 부탁 드립니다.", member, careTarget, careTargetSchedule);

                JobOffer jobOffer2 = jobOffer("title1", LocalDate.now(), LocalDate.now(), "desiredDayWeek1", "10:00",
                                "10:30", 1, 10, Gender.M, "comment1", member, careTarget, careTargetSchedule);
                JobOffer jobOffer3 = jobOffer("title2", LocalDate.now(), LocalDate.now(), "desiredDayWeek2", "11:00",
                                "11:30", 2, 20, Gender.M, "comment2", member, careTarget, careTargetSchedule);
                JobOffer jobOffer4 = jobOffer("title3", LocalDate.now(), LocalDate.now(), "desiredDayWeek3", "12:00",
                                "12:30", 3, 20, Gender.M, "comment3", member, careTarget2, careTargetSchedule);
                JobOffer jobOffer5 = jobOffer("title4", LocalDate.now(), LocalDate.now(), "desiredDayWeek4", "13:00",
                                "13:30", 4, 40, Gender.M, "comment4", member, careTarget2, careTargetSchedule);
                JobOffer jobOffer6 = jobOffer("title5", LocalDate.now(), LocalDate.now(), "desiredDayWeek5", "14:00",
                                "14:30", 5, 50, Gender.M, "comment5", member, careTarget, careTargetSchedule);
                System.out.println("jobOffer1 = " + jobOffer1);
        }


        private CareTarget careTarget(String name, Gender gender, LocalDate birth, Long height, Long weight,
                        String zipcode, String address, String detailedAddress, int longTermCareGrade, String comment,
                        int pet, int isCctvAgreement, String careType, int coronaTest, Member member) {
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

        private JobOffer jobOffer(String title, LocalDate startDate, LocalDate endDate, String desiredDayWeek,
                        String desiredStartTime, String desiredEndTime, int pay, int amount,
                        Gender desiredCareSitterGender, String comment, Member member, CareTarget careTarget,
                        CareTargetSchedule careTargetSchedule) {
                JobOffer jobOffer = JobOffer.builder()
                                .title(title)
                                .startDate(startDate)
                                .endDate(endDate)
                                .day(desiredDayWeek)
                                .desiredStartTime(desiredStartTime)
                                .desiredEndTime(desiredEndTime)
                                .pay(pay)
                                .amount(amount)
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

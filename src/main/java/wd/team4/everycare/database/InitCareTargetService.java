package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.*;
import wd.team4.everycare.repository.MemberRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class InitCareTargetService {

    private final EntityManager em;
    private final MemberRepository memberRepository;

    public void dbInit() {
        Optional<Member> member1 = memberRepository.findById("member1");
        Optional<Member> member3 = memberRepository.findById("member14");

        CareTarget careTarget1 = createCareTarget("표철진", Gender.M, "1954-09-14", 170, 70, "12345", "대구광역시", "북구", 2, "거동이 불편합니다.", 0, 0, "노인", 1, member1.get());
        em.persist(careTarget1);
        CareTargetImage careTargetImage1 = createCareTargetImage("target1.png", "target1.png", careTarget1);
        em.persist(careTargetImage1);

        CareTarget careTarget2 = createCareTarget("나담비", Gender.W, "2018-12-09", 101, 16, "73434", "대구광역시", "중구", 0, "잘 부탁 드립니다.", 0, 1, "아동", 1, member3.get());
        em.persist(careTarget2);
        CareTargetImage careTargetImage2 = createCareTargetImage("target2.jpg", "target2.jpg", careTarget2);
        em.persist(careTargetImage2);
    }

    private CareTarget createCareTarget(String name, Gender gender, String birth, int height, int weight, String zipcode, String address, String detailedAddress, int longTermCareGrade, String comment, int pet, int isCctvAgreement, String careType, int coronaTest, Member member) {
        CareTarget careTarget = CareTarget.builder()
                .name(name)
                .gender(gender)
                .birth(LocalDate.parse(birth))
                .height((long)height)
                .weight((long)weight)
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
        return careTarget;
    }

    private CareTargetImage createCareTargetImage(String uploadFileName, String storeFileName, CareTarget careTarget) {

        CareTargetImage careTargetImage = CareTargetImage.builder()
                .uploadFileName(uploadFileName)
                .storeFileName(storeFileName)
                .careTarget(careTarget)
                .build();

        em.persist(careTargetImage);
        return careTargetImage;
    }
}


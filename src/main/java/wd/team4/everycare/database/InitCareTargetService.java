package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.Gender;
import wd.team4.everycare.domain.Member;
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
        CareTarget careTarget = createCareTarget("표철진", Gender.M, "1954-09-14", 170, 70, "12345", "대구광역시", "북구", 2, "거동이 불편합니다.", 0, 0, "노인", 1, member1.get());
        em.persist(careTarget);
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
}


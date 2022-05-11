package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.CareSitterImage;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.repository.MemberRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class InitCareSitterService {

    private final EntityManager em;
    private final MemberRepository memberRepository;
    private String[] name = new String[]{"오석호", "최용진", "예영하", "허홍주", "오인숙"};
    private String[] preferredType = new String[]{"아동", "노인", "아동", "환자", "노인"};
    private String[] hopefulRegion = new String[]{"대구광역시 동구", "대구광역시 수성구", "대구광역시 남구", "대구광역시 중구", "대구광역시 북구"};
    private String[] desiredDayWeek = new String[]{"월,화,수", "화,수,목,금", "월,수,금", "월,화,수,목,금", "월,화,수,목,금"};
    private String[] activityTime = new String[]{"4", "8", "5", "10", "9"};
    private String[] desiredHourlyWage = new String[]{"10000", "12000", "10000", "10000", "10000"};
    private String[] desiredMonthlyWage = new String[]{"60만", "240만", "80만", "260만", "180만"};
    private int[] cctvAgreement = new int[]{1, 1, 1, 1, 1};
    private int[] vaccination = new int[]{1, 1, 1, 1, 1};
    private String[] introduction = new String[]{"잘 부탁드립니다!", "열심히 하겠습니다.", "안녕하세요 잘 부탁드립니다.",
            "가족을 돌보는 마음으로 돌보겠습니다.", "잘 부탁드립니다."};

    public void dbInit() {

        Optional<Member> member1 = memberRepository.findById("member1");
        Optional<Member> member2 = memberRepository.findById("member2");
        Optional<Member> member3 = memberRepository.findById("member3");
        Optional<Member> member4 = memberRepository.findById("member4");
        Optional<Member> member5 = memberRepository.findById("member5");

        CareSitter careSitter1 = createCareSitter(name[0], preferredType[0], hopefulRegion[0], desiredDayWeek[0], activityTime[0], desiredHourlyWage[0],
                desiredMonthlyWage[0], cctvAgreement[0], vaccination[0], introduction[0], 1, LocalDateTime.now(), LocalDateTime.now(), member1.get());
        CareSitter careSitter2 = createCareSitter(name[1], preferredType[1], hopefulRegion[1], desiredDayWeek[1], activityTime[1], desiredHourlyWage[1],
                desiredMonthlyWage[1], cctvAgreement[1], vaccination[1], introduction[1], 1, LocalDateTime.now(), LocalDateTime.now(), member2.get());
        CareSitter careSitter3 = createCareSitter(name[2], preferredType[2], hopefulRegion[2], desiredDayWeek[2], activityTime[2], desiredHourlyWage[2],
                desiredMonthlyWage[2], cctvAgreement[2], vaccination[2], introduction[2], 1, LocalDateTime.now(), LocalDateTime.now(), member3.get());
        CareSitter careSitter4 = createCareSitter(name[3], preferredType[3], hopefulRegion[3], desiredDayWeek[3], activityTime[3], desiredHourlyWage[3],
                desiredMonthlyWage[3], cctvAgreement[3], vaccination[3], introduction[3], 1, LocalDateTime.now(), LocalDateTime.now(), member4.get());
        CareSitter careSitter5 = createCareSitter(name[4], preferredType[4], hopefulRegion[4], desiredDayWeek[4], activityTime[4], desiredHourlyWage[4],
                desiredMonthlyWage[4], cctvAgreement[4], vaccination[4], introduction[4], 1, LocalDateTime.now(), LocalDateTime.now(), member5.get());

        createCareSitterImage("sitter1.jpg", "sitter1.jpg", careSitter1);
        createCareSitterImage("sitter2.jpg", "sitter2.jpg", careSitter2);
        createCareSitterImage("sitter3.jpg", "sitter3.jpg", careSitter3);
        createCareSitterImage("sitter4.jpg", "sitter4.jpg", careSitter4);
        createCareSitterImage("sitter5.jpg", "sitter5.jpg", careSitter5);

    }

    private CareSitter createCareSitter(String name, String preferredType, String hopefulRegion, String desiredDayWeek, String activityTime,
                                        String desiredHourlyWage, String desiredMonthlyWage, int cctvAgreement, int vaccination, String introduction,
                                        int disclosureStatus, LocalDateTime createdAt, LocalDateTime updatedAt, Member member) {

        CareSitter careSitter = CareSitter.builder()
                .name(name)
                .preferredType(preferredType)
                .hopefulRegion(hopefulRegion)
                .desiredDayWeek(desiredDayWeek)
                .activityTime(activityTime)
                .desiredHourlyWage(desiredHourlyWage)
                .desiredMonthlyWage(desiredMonthlyWage)
                .cctvAgreement(cctvAgreement)
                .vaccination(vaccination)
                .introduction(introduction)
                .disclosureStatus(disclosureStatus)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .member(member)
                .build();

        em.persist(careSitter);
        return careSitter;
    }

    private CareSitterImage createCareSitterImage(String uploadFileName, String storeFileName, CareSitter careSitter) {

        CareSitterImage careSitterImage = CareSitterImage.builder()
                .uploadFileName(uploadFileName)
                .storeFileName(storeFileName)
                .careSitter(careSitter)
                .build();

        em.persist(careSitterImage);
        return careSitterImage;
    }
}

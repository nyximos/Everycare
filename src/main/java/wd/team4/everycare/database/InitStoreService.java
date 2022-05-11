package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.Store;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.repository.StoreRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class InitStoreService {

    private final EntityManager em;

    private final MemberRepository memberRepository;

    public void dbInit() {

        Optional<Member> member1 = memberRepository.findById("member1");
        createStore("굿케어", "www.goodcare", 123456789, "good@care",
                "09:00", "06:00", "12:00", "13:00",
                "토,일", "굿케어", "허윤정", "부산광역시 북구","1234-4567",member1.get());

    }

    private Store createStore(String name, String url, int businessLicenseNumber, String email,
                              String operationStartTime, String operationEndTime, String lunchStartTime, String lunchEndTime,
                              String closedDay, String companyCorporationName, String representativeName,
                              String businessLocation, String customerServiceNumber, Member member) {
        Store store = Store.builder()
                .name(name)
                .url(url)
                .businessLicenseNumber(businessLicenseNumber)
                .email(email)
                .operationStartTime(operationStartTime)
                .operationEndTime(operationEndTime)
                .lunchStartTime(lunchStartTime)
                .lunchEndTime(lunchEndTime)
                .closedDay(closedDay)
                .companyCorporationName(companyCorporationName)
                .representativeName(representativeName)
                .businessLocation(businessLocation)
                .customerServiceNumber(customerServiceNumber)
                .member(member)
                .build();

        store.saveTime(LocalDateTime.now());
        em.persist(store);

        return store;
    }
}

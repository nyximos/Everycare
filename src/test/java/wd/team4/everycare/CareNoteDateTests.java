package wd.team4.everycare;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.CareNote;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.Contract;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.repository.CareNoteRepository;
import wd.team4.everycare.repository.query.MemberCareNoteQueryRepository;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)  // spring이랑 같이 엮어서 실행, 스프링과 테스트 통합
@SpringBootTest               // springBoot 띄운 상태에서 테스트, 이게 없으면 @Autowired 다 실패
@Transactional                // 변경 작업할 때 필수, 테스트 끝나면 트랜잭션 강제로 롤백, 반복 가능한 테스트 지원public class DateTest {
public class CareNoteDateTests {

    @Autowired
    MemberCareNoteQueryRepository careNoteQueryRepository;
    
    @Autowired
    CareNoteRepository careNoteRepository;
    
    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    public void contracts() {

        Optional<CareNote> careNote = careNoteRepository.findById(1L);
        CareNote careNoteEntity = careNote.orElse(null);

        CareTarget careTarget = careNoteEntity.getContract().getJobOffer().getCareTarget();

        System.out.println("careTarget.getName() = " + careTarget.getName());
        Member member = careNoteEntity.getMember();

        List<Contract> contracts = careNoteQueryRepository.findContracts(member, careTarget);

        for (Contract contract : contracts) {
            System.out.println("contract.getName() = " + contract.getName());
            System.out.println("contract.getStatus() = " + contract.getStatus());
        }
        
    }

}

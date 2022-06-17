package wd.team4.everycare.repository.query;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.*;

import java.time.LocalDate;
import java.util.List;

import static wd.team4.everycare.domain.QCareNote.*;
import static wd.team4.everycare.domain.QContract.*;
import static wd.team4.everycare.domain.QJobOffer.*;

@Repository
@RequiredArgsConstructor
public class MemberCareNoteQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Contract> findContracts(Member member, CareTarget careTarget) {
        return queryFactory
                .selectFrom(contract)
                .join(contract.jobOffer, jobOffer).fetchJoin()
                .where(jobOffer.careTarget.eq(careTarget),
                        contract.member.eq(member),
                        contract.status.eq(2))
                .fetch();
    }

}

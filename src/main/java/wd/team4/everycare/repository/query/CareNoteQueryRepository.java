package wd.team4.everycare.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.CareNote;
import wd.team4.everycare.domain.CareSitter;

import java.util.List;

import static wd.team4.everycare.domain.QCareNote.*;


@Repository
@RequiredArgsConstructor
public class CareNoteQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<CareNote> findAllByCareSitterAndContractId(CareSitter careSitter, Long id) {
        return queryFactory
                .selectFrom(careNote)
                .where(careNote.endTime.isNotNull(),
                        careNote.careSitter.eq(careSitter),
                        careNote.contract.id.eq(id))
                .fetch();
    }
}

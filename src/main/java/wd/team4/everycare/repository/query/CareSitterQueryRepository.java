package wd.team4.everycare.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.CareSitter;

import java.util.List;

import static wd.team4.everycare.domain.QCareSitter.*;

@Repository
@RequiredArgsConstructor
public class CareSitterQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<CareSitter> findAllByRegion(String region) {
        return queryFactory
                .selectFrom(careSitter)
                .where(careSitter.hopefulRegion.contains(region),
                        careSitter.disclosureStatus.eq(1))
                .fetch();
    }

    public List<CareSitter> findAllByCareType(String type) {
        return queryFactory
                .selectFrom(careSitter)
                .where(careSitter.preferredType.contains(type),
                        careSitter.disclosureStatus.eq(1))
                .fetch();
    }

    public List<CareSitter> findAllByActivityTime(String time) {
        return queryFactory
                .selectFrom(careSitter)
                .where(careSitter.activityTime.eq(time),
                        careSitter.disclosureStatus.eq(1))
                .fetch();
    }
}

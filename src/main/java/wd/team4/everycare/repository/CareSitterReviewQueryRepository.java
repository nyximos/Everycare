package wd.team4.everycare.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.ActivityClassification;
import wd.team4.everycare.domain.CareSitter;

import java.util.List;

import static wd.team4.everycare.domain.QActivityClassification.activityClassification1;
import static wd.team4.everycare.domain.QActivityInformation.activityInformation;
import static wd.team4.everycare.domain.QCareSitterReview.careSitterReview;

@RequiredArgsConstructor
@Repository
public class CareSitterReviewQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<ActivityClassification> findAllCategory(Long scheduleId) {
        return queryFactory
                .select(activityClassification1)
                .from(activityClassification1)
                .join(activityInformation)
                .on(activityInformation.activityClassification.id.eq(activityClassification1.id))
                .where(activityInformation.careTargetSchedule.id.eq(scheduleId))
                .distinct()
                .fetch();
    }

    public List<Tuple> findActivityClassification(CareSitter careSitter){
        return queryFactory
                .select(careSitterReview.activityClassification, careSitterReview.rating.avg(), careSitterReview.count())
                .from(careSitterReview)
                .where(careSitterReview.careSitter.eq(careSitter))
                .groupBy(careSitterReview.activityClassification)
                .distinct()
                .fetch();
    }
}

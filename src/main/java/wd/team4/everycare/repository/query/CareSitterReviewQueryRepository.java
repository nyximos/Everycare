package wd.team4.everycare.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.ActivityClassification;
import wd.team4.everycare.domain.ActivityInformation;
import wd.team4.everycare.domain.QActivityClassification;

import java.util.List;

import static wd.team4.everycare.domain.QActivityClassification.*;
import static wd.team4.everycare.domain.QActivityInformation.*;
import static wd.team4.everycare.domain.QCareTargetSchedule.*;

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
}

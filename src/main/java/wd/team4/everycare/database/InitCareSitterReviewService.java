package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.*;
import wd.team4.everycare.repository.ActivityClassificationRepository;
import wd.team4.everycare.repository.CareSitterRepository;
import wd.team4.everycare.repository.CareTargetScheduleRepository;
import wd.team4.everycare.repository.MemberRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@Transactional
@RequiredArgsConstructor
public class InitCareSitterReviewService {

    private final EntityManager em;
    private final MemberRepository memberRepository;
    private final CareTargetScheduleRepository careTargetScheduleRepository;
    private final CareSitterRepository careSitterRepository;
    private final ActivityClassificationRepository activityClassificationRepository;

    public void dbInit() {
        Member member1 = memberRepository.findById("member1").get();
        Member member2 = memberRepository.findById("member2").get();
        CareTargetSchedule careTargetSchedule = careTargetScheduleRepository.findById(1L).get();
        CareSitter careSitter = careSitterRepository.findByMember(member1);
        ActivityClassification activityClassification1 = activityClassificationRepository.findById(1L).get();
        ActivityClassification activityClassification2 = activityClassificationRepository.findById(2L).get();
        ActivityClassification activityClassification3 = activityClassificationRepository.findById(3L).get();
        ActivityClassification activityClassification4 = activityClassificationRepository.findById(4L).get();
        ActivityClassification activityClassification5 = activityClassificationRepository.findById(5L).get();

        CareSitterReview careSitterReview1 = createCareSitterReview(5L, "comment1", LocalDateTime.now(), LocalDateTime.now(), activityClassification1, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview2 = createCareSitterReview(4L, "comment2", LocalDateTime.now(), LocalDateTime.now(), activityClassification2, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview3 = createCareSitterReview(3L, "comment3", LocalDateTime.now(), LocalDateTime.now(), activityClassification3, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview4 = createCareSitterReview(4L, "comment4", LocalDateTime.now(), LocalDateTime.now(), activityClassification4, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview5 = createCareSitterReview(5L, "comment5", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview6 = createCareSitterReview(5L, "comment6", LocalDateTime.now(), LocalDateTime.now(), activityClassification1, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview7 = createCareSitterReview(4L, "comment7", LocalDateTime.now(), LocalDateTime.now(), activityClassification2, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview8 = createCareSitterReview(3L, "comment8", LocalDateTime.now(), LocalDateTime.now(), activityClassification3, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview9 = createCareSitterReview(4L, "comment9", LocalDateTime.now(), LocalDateTime.now(), activityClassification4, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview10 = createCareSitterReview(5L, "comment10", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview11 = createCareSitterReview(5L, "comment11", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview12 = createCareSitterReview(5L, "comment12", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview13 = createCareSitterReview(5L, "comment13", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview14 = createCareSitterReview(5L, "comment14", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview15 = createCareSitterReview(5L, "comment15", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview16 = createCareSitterReview(5L, "comment16", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview17 = createCareSitterReview(5L, "comment17", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview18 = createCareSitterReview(5L, "comment18", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview19 = createCareSitterReview(5L, "comment19", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview20 = createCareSitterReview(5L, "comment20", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview21 = createCareSitterReview(5L, "comment21", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview22 = createCareSitterReview(5L, "comment22", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview23 = createCareSitterReview(5L, "comment23", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview24 = createCareSitterReview(5L, "comment24", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview25 = createCareSitterReview(5L, "comment25", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview26 = createCareSitterReview(5L, "comment26", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview27 = createCareSitterReview(5L, "comment27", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview28 = createCareSitterReview(5L, "comment28", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview29 = createCareSitterReview(5L, "comment29", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview30 = createCareSitterReview(5L, "comment30", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview31 = createCareSitterReview(5L, "comment31", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview32 = createCareSitterReview(5L, "comment32", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
        CareSitterReview careSitterReview33 = createCareSitterReview(5L, "comment33", LocalDateTime.now(), LocalDateTime.now(), activityClassification5, careSitter, member2, careTargetSchedule);
    }

    private CareSitterReview createCareSitterReview(Long rating, String comment, LocalDateTime createdAt, LocalDateTime updatedAt, ActivityClassification activityClassification, CareSitter careSitter, Member member, CareTargetSchedule careTargetSchedule) {
        CareSitterReview careSitterReview = CareSitterReview.builder()
                .rating(rating)
                .comment(comment)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .activityClassification(activityClassification)
                .careSitter(careSitter)
                .member(member)
                .careTargetSchedule(careTargetSchedule)
                .build();

        em.persist(careSitterReview);

        return careSitterReview;
    }
}

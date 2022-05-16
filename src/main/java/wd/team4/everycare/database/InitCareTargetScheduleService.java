package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.ActivityClassification;
import wd.team4.everycare.domain.ActivityInformation;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetSchedule;
import wd.team4.everycare.repository.ActivityClassificationRepository;
import wd.team4.everycare.repository.CareTargetRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class InitCareTargetScheduleService {

    private final EntityManager em;
    private final CareTargetRepository careTargetRepository;
    private final ActivityClassificationRepository activityClassificationRepository;

    public void dbInit() {
        Optional<CareTarget> careTarget = careTargetRepository.findById((long)1);
        CareTargetSchedule careTargetSchedule = createCareTargetSchedule("일상", "09:00", "18:00", careTarget.get());

        Optional<ActivityClassification> activityClassification1 = activityClassificationRepository.findById(50L);
        Optional<ActivityClassification> activityClassification2 = activityClassificationRepository.findById(51L);
        Optional<ActivityClassification> activityClassification3 = activityClassificationRepository.findById(55L);
        Optional<ActivityClassification> activityClassification4 = activityClassificationRepository.findById(58L);
        Optional<ActivityClassification> activityClassification5 = activityClassificationRepository.findById(25L);


        createActivityInformation("10:00", "10:30", "깨끗이 해주세요.", activityClassification1.get(), careTargetSchedule);
        createActivityInformation("10:00", "11:00", "깨끗이 해주세요.", activityClassification2.get(), careTargetSchedule);
        createActivityInformation("12:00", "13:00", "식사 차림만 해주세요.", activityClassification3.get(), careTargetSchedule);
        createActivityInformation("13:00", "13:30", null, activityClassification4.get(), careTargetSchedule);
        createActivityInformation("13:30", "14:30", "고민나눔", activityClassification5.get(), careTargetSchedule);
    }

    private CareTargetSchedule createCareTargetSchedule(String name, String startTime, String endTime, CareTarget careTarget) {
        CareTargetSchedule careTargetSchedule = CareTargetSchedule.builder()
                .name(name)
                .startTime(startTime)
                .endTime(endTime)
                .careTarget(careTarget)
                .build();
        em.persist(careTargetSchedule);
        return careTargetSchedule;
    }

    private ActivityInformation createActivityInformation(String startTime, String endTime, String requirement, ActivityClassification activityClassification, CareTargetSchedule careTargetSchedule) {
        ActivityInformation activityInformation = ActivityInformation.builder()
                .startTime(startTime)
                .endTime(endTime)
                .requirement(requirement)
                .activityClassification(activityClassification)
                .careTargetSchedule(careTargetSchedule)
                .build();
        em.persist(activityInformation);
        return activityInformation;
    }
}

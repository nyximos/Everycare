package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.ActivityInformation;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetSchedule;
import wd.team4.everycare.repository.CareTargetRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class InitCareTargetScheduleService {

    private final EntityManager em;
    private final CareTargetRepository careTargetRepository;

    public void dbInit() {
        Optional<CareTarget> careTarget = careTargetRepository.findById((long)1);
        CareTargetSchedule careTargetSchedule = createCareTargetSchedule("일상", "09:00", "18:00", careTarget.get());
        em.persist(careTargetSchedule);

        createActivityInformation("10:00", "10:30", "깨끗이 해주세요.", careTargetSchedule);
        createActivityInformation("10:00", "11:00", "깨끗이 해주세요.", careTargetSchedule);
        createActivityInformation("12:00", "13:00", "식사 차림만 해주세요.", careTargetSchedule);
        createActivityInformation("13:00", "13:30", null, careTargetSchedule);
        createActivityInformation("13:30", "14:30", "고민나눔", careTargetSchedule);
    }

    private CareTargetSchedule createCareTargetSchedule(String name, String startTime, String endTime, CareTarget careTarget) {
        CareTargetSchedule careTargetSchedule = CareTargetSchedule.builder()
                .name(name)
                .startTime(startTime)
                .endTime(endTime)
                .careTarget(careTarget)
                .build();
        return careTargetSchedule;
    }

    private ActivityInformation createActivityInformation(String startTime, String endTime, String requirement, CareTargetSchedule careTargetSchedule) {
        ActivityInformation activityInformation = ActivityInformation.builder()
                .startTime(startTime)
                .endTime(endTime)
                .requirement(requirement)
                .careTargetSchedule(careTargetSchedule)
                .build();
        em.persist(activityInformation);
        return activityInformation;
    }
}

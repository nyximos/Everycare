package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.HealthClassification;

import javax.persistence.EntityManager;

@Component
@Transactional
@RequiredArgsConstructor
public class InitHealthClassificationService {

    private final EntityManager em;

    public void dbInIt() {

        createHealthClassification("신체기능");
        createHealthClassification("식사기능");
        createHealthClassification("인지기능");

    }

    private HealthClassification createHealthClassification(String name) {
        HealthClassification healthClassification = HealthClassification.builder()
                .name(name)
                .build();

        em.persist(healthClassification);
        return healthClassification;
    }
}

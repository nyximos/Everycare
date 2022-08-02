package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.Bedge;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.CareSitterBedge;
import wd.team4.everycare.repository.BadgeRepository;
import wd.team4.everycare.repository.CareSitterRepository;

import javax.persistence.EntityManager;

@Component
@Transactional
@RequiredArgsConstructor
public class InitCareSitterBadgeService {

    private final EntityManager em;
    private final CareSitterRepository careSitterRepository;
    private final BadgeRepository badgeRepository;

    public void dbInit(){



        CareSitter caresitter1 = careSitterRepository.findById(1L).get();
        CareSitter caresitter2 = careSitterRepository.findById(2L).get();

        Bedge bedge1 = badgeRepository.findById(1L).orElse(null);
        Bedge bedge2 = badgeRepository.findById(2L).orElse(null);
        Bedge bedge3 = badgeRepository.findById(3L).orElse(null);
        Bedge bedge4 = badgeRepository.findById(4L).orElse(null);
        Bedge bedge5 = badgeRepository.findById(5L).orElse(null);
        Bedge bedge6 = badgeRepository.findById(6L).orElse(null);
        Bedge bedge7 = badgeRepository.findById(7L).orElse(null);
        Bedge bedge8 = badgeRepository.findById(8L).orElse(null);

        CareSitterBedge careSitterBedge1 = careSitterBedge(caresitter1, bedge1);
        CareSitterBedge careSitterBedge2 = careSitterBedge(caresitter1, bedge2);
        CareSitterBedge careSitterBedge3 = careSitterBedge(caresitter1, bedge3);
        CareSitterBedge careSitterBedge4 = careSitterBedge(caresitter1, bedge4);
        CareSitterBedge careSitterBedge5 = careSitterBedge(caresitter2, bedge5);
        CareSitterBedge careSitterBedge6 = careSitterBedge(caresitter2, bedge6);
        CareSitterBedge careSitterBedge7 = careSitterBedge(caresitter2, bedge7);
        CareSitterBedge careSitterBedge8 = careSitterBedge(caresitter2, bedge8);

    }

    public CareSitterBedge careSitterBedge(CareSitter careSitter, Bedge badge){
        CareSitterBedge careSitterBedge = CareSitterBedge.builder()
                .careSitter(careSitter)
                .bedge(badge)
                .build();

        em.persist(careSitterBedge);
        return careSitterBedge;
    }
}

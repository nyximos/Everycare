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
import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class InitCareSitterBadgeService {

    private final EntityManager em;
    private final CareSitterRepository careSitterRepository;
    private final BadgeRepository badgeRepository;

    public void dbInit(){

        List<CareSitter> all = careSitterRepository.findAll();

        for (CareSitter careSitters: all) {
            careSitters.toString();
        }

        CareSitter careSitter = careSitterRepository.findById(1L).get();

        System.out.println("careSitter = " + careSitter);
        Bedge bedge1 = badgeRepository.findById(1L).orElse(null);
        Bedge bedge2 = badgeRepository.findById(2L).orElse(null);
        Bedge bedge3 = badgeRepository.findById(3L).orElse(null);
        Bedge bedge4 = badgeRepository.findById(4L).orElse(null);
        Bedge bedge5 = badgeRepository.findById(5L).orElse(null);
        Bedge bedge6 = badgeRepository.findById(6L).orElse(null);
        Bedge bedge7 = badgeRepository.findById(7L).orElse(null);
        Bedge bedge8 = badgeRepository.findById(8L).orElse(null);

        CareSitterBedge careSitterBedge1 = careSitterBedge(careSitter, bedge1);
        CareSitterBedge careSitterBedge2 = careSitterBedge(careSitter, bedge2);
        CareSitterBedge careSitterBedge3 = careSitterBedge(careSitter, bedge3);
        CareSitterBedge careSitterBedge4 = careSitterBedge(careSitter, bedge4);
        CareSitterBedge careSitterBedge5 = careSitterBedge(careSitter, bedge5);
        CareSitterBedge careSitterBedge6 = careSitterBedge(careSitter, bedge6);
        CareSitterBedge careSitterBedge7 = careSitterBedge(careSitter, bedge7);
        CareSitterBedge careSitterBedge8 = careSitterBedge(careSitter, bedge8);

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

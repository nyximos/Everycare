package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.CareSitterReview;
import wd.team4.everycare.repository.CareSitterRepository;
import wd.team4.everycare.repository.CareSitterReviewRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class InitCareSitterBadgeService {

    private final EntityManager em;
    private CareSitterRepository careSitterRepository;
    private CareSitterReviewRepository careSitterReviewRepository;

    /* TODO 케어시터 뱃지 추가 */
    public void dbInit(){
        CareSitter careSitter = careSitterRepository.findById(1L).orElse(null);
        List<CareSitterReview> careSitterList = careSitterReviewRepository.findByCareSitter(careSitter);

    }
}

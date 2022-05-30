package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.CareSitterReview;

import java.util.List;

public interface CareSitterReviewRepository extends JpaRepository<CareSitterReview, Long> {
    List<CareSitterReview> findByCareSitter(CareSitter careSitter);
}

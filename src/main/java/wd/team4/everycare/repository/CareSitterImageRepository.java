package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.CareSitterImage;

import java.util.List;

public interface CareSitterImageRepository extends JpaRepository<CareSitterImage, Long> {

    @EntityGraph(attributePaths = {"careSitter"})
    List<CareSitterImage> findByCareSitterId(Long id);

    List<CareSitterImage> findAllByCareSitter(CareSitter careSitter);
}

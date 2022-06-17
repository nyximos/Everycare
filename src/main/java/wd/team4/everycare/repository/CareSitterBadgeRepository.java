package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.Bedge;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.CareSitterBedge;

import java.util.List;
import java.util.Optional;

@Repository
public interface CareSitterBadgeRepository extends JpaRepository<CareSitterBedge, Long> {
    Optional<CareSitterBedge> findByBedge(Bedge badge);

    List<CareSitterBedge> findByCareSitter(CareSitter careSitter);

    @EntityGraph(attributePaths = {"careSitter"})
    List<CareSitterBedge> findByCareSitterId(Long careSitterId);
}

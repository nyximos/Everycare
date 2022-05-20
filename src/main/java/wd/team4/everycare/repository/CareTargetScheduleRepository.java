package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.CareTargetSchedule;

import java.util.List;


public interface CareTargetScheduleRepository extends JpaRepository<CareTargetSchedule, Long> {

    @EntityGraph(attributePaths = {"careTarget"})
    List<CareTargetSchedule> findAllByCareTargetId(Long id);

    List<CareTargetSchedule> findByCareTargetId(Long id);

    @EntityGraph(attributePaths = {"careNote"})
    CareTargetSchedule findByCareNoteId(Long id);
}

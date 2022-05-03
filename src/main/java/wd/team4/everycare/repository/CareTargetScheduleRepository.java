package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.CareTargetSchedule;

import java.util.List;

public interface CareTargetScheduleRepository extends JpaRepository<CareTargetSchedule, Long> {
    List<CareTargetSchedule> findByCareTargetId(Long id);
}
